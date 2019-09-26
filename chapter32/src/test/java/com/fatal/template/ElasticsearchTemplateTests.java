package com.fatal.template;

import com.fatal.Chapter32ApplicationTests;
import com.fatal.component.CustomResultMapper;
import com.fatal.entity.City;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/9/24 0024 8:40
 */
public class ElasticsearchTemplateTests extends Chapter32ApplicationTests {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private CustomResultMapper customResultMapper;

    /**
     * 新增文档
     */
    @Test
    public void indexTest() {
        City city = City.builder()
                .name("海贼王")
                .theDetail("《航海王》是日本漫画家尾田荣一郎作画的少年漫画作品，在《周刊少年Jump》1997年第34号开始连载，电子版由漫番漫画连载。改编的电视动画《航海王》于1999年10月20日起在富士电...")
                .build();
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(city)
                .build();
        String documentId = template.index(indexQuery);
        System.out.println(documentId);
    }

    /**
     * 批量新增文档
     */
    @Test
    public void bulkIndexTest() {
        List<IndexQuery> queries = Arrays.asList(
                new IndexQueryBuilder()
                        .withObject(City.builder().name("重庆").theDetail("重庆文化"))
                        .build(),
                new IndexQueryBuilder()
                        .withObject(City.builder().name("长沙").theDetail("长沙文化"))
                        .build()
        );
        template.bulkIndex(queries);
    }

    @Test
    public void queryStringTest() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("惠州"))
                .withPageable(PageRequest.of(0, 5))
                .build();
        List<City> cities = template.queryForList(searchQuery, City.class);
        cities.forEach(System.out::println);
    }

    @Test
    public void matchQueryTest() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("theDetail", "美食"))
                .withPageable(PageRequest.of(0, 5))
                .build();
        List<City> cities = template.queryForList(searchQuery, City.class);
        cities.forEach(System.out::println);
    }

    /**
     * 测试高亮查询
     */
    @Test
    public void highLightTest() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("theDetail", "美食");
        // 指定高亮的class属性值之后，具体样式交给前端设计。支持设置多种class属性值。
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags(HighlightBuilder.DEFAULT_STYLED_PRE_TAG)
                .postTags(HighlightBuilder.DEFAULT_STYLED_POST_TAGS);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("theDetail"))
                .withHighlightBuilder(highlightBuilder)
                .build();
        Page<City> page = template.queryForPage(searchQuery, City.class, customResultMapper);
        page.forEach(System.out::println);
    }


}
