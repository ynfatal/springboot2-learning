package com.fatal.component;

import org.apache.commons.beanutils.PropertyUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义 CustomResultMapper
 * @author Fatal
 * @date 2019/9/3 0003 18:31
 * @desc: 对返回结果进行高亮加工
 */
@Component
public class CustomResultMapper extends DefaultResultMapper {

	private static final String ID = "id";

	/**
	 * 填充高亮数据
	 * @param response
	 * @param clazz
	 * @param pageable
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
		AggregatedPage<T> aggregatedPage = super.mapResults(response, clazz, pageable);
		Map<String, Map<String, HighlightField>> highLightMap = Arrays.stream(response.getHits().getHits())
				.collect(Collectors.toMap(SearchHit::getId, SearchHit::getHighlightFields));
		aggregatedPage.forEach(t -> {
			try {
				Object id = PropertyUtils.getProperty(t, ID);
				Map<String, HighlightField> highlightFields = highLightMap.get(id);
				populateHighLightedData(t, highlightFields);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		});
		return aggregatedPage;
	}

	/**
	 * 填充高亮数据
	 * @param result
	 * @param highlightFields
	 * @param <T>
	 */
	private <T> void populateHighLightedData(T result, Map<String, HighlightField> highlightFields) {
		for (HighlightField field : highlightFields.values()) {
			try {
				PropertyUtils.setProperty(result, field.getName(), concat(field.fragments()));
			} catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
				throw new ElasticsearchException("failed to set highlighted value for field: " + field.getName()
						+ " with value: " + Arrays.toString(field.getFragments()), e);
			}
		}
	}

	/**
	 * 连接多个片段的值，片段数量对应 Stream#readVInt，片段值对应 StreamInput#readText
	 * 文本字数太多的话，不会一整块，而是将包含有关键字的部分一小段一小段放数组中。当然，保存到es中的文字数目要不要限制就看场景吧
	 * @param fragments
	 * @return
	 */
	private Object concat(Text[] fragments) {
		StringBuilder sb = new StringBuilder();
		for (Text text : fragments) {
			sb.append(text.toString());
		}
		return sb.toString();
	}
}
