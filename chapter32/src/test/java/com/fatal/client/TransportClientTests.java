package com.fatal.client;

import com.fatal.Chapter32ApplicationTests;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author Fatal
 * @date 2019/9/23 0023 9:55
 */
public class TransportClientTests extends Chapter32ApplicationTests {

    @Autowired
    private TransportClient client;

    /**
     * 根据 id 查询文档
     */
    @Test
    public void prepareGetTest() {
        GetResponse getResponse = client.prepareGet("city", "city_search", "1d90Zm0BEkPQvsGlQXmi").get();
        Map<String, Object> source = getResponse.getSourceAsMap();
        if (!CollectionUtils.isEmpty(source)) {
            System.out.println(source.get("id"));
            System.out.println(source.get("name"));
            System.out.println(source.get("theDetail"));
        }
    }

    /**
     * 新增文档
     */
    @Test
    public void indexTest() {
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name", "佛山")
                    .field("theDetail", "佛山文化")
                    .endObject();
            IndexResponse indexResponse = client.prepareIndex("city", "city_search")
                    .setSource(xContentBuilder)
                    .get();
            System.out.println(indexResponse.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新文档
     */
    @Test
    public void updateTest() {
        UpdateRequest updateRequest = new UpdateRequest("city", "city_search", "uN_0ZW0BEkPQvsGlN3m_");
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name", "北京")
                    .field("theDetail", "北京文化")
                    .endObject();
            updateRequest.doc(xContentBuilder);
            UpdateResponse updateResponse = null;
            try {
                updateResponse = client.update(updateRequest).get();
                System.out.println(updateResponse.getResult().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 id 删除文档
     */
    @Test
    public void prepareDeleteTest() {
        DeleteResponse deleteResponse = client.prepareDelete("city", "city_search", "ud_-ZW0BEkPQvsGlBnl1").get();
        System.out.println(deleteResponse.getResult());
    }


    // ---------------- 查询 ----------------

    @Test
    public void idsQueryTest() {
        IdsQueryBuilder queryBuilder = QueryBuilders.idsQuery()
                .addIds("uN_0ZW0BEkPQvsGlN3m_", "tN_uZW0BEkPQvsGlu3m_");
        analysis(search(queryBuilder));
    }

    /**
     * 单个字段匹配查询
     */
    @Test
    public void termQueryTest() {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("theDetail", "汕头");
        analysis(search(queryBuilder));
    }

    /**
     * 单个字段匹配查询
     */
    @Test
    public void matchQueryTest() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("theDetail", "潮汕美食");
        analysis(search(queryBuilder));
    }

    /**
     * 匹配全部文档（这里匹配全部是指匹配指定索引和类型下的全部文档）
     */
    @Test
    public void matchAllQueryTest() {
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        analysis(search(queryBuilder));
    }

    /**
     * 多个字段匹配某个值
     */
    @Test
    public void multiMatchQueryTest() {
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("上海", "name", "theDetail");
        analysis(search(queryBuilder));
    }

    /**
     * 测试 term 和 match_phrase
     * term: 不分词
     * match_phrase: 分词之后逐一搜索，返回各个结果的并集
     */
    @Test
    public void termQueryWithMatchPhraseQueryTest() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("theDetail", "我是中国人");
        System.out.println("  ============  termQuery  ==========  ");
        analysis(search(termQueryBuilder));
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("theDetail", "我是中国人");
        System.out.println("  ============  matchPhraseQuery  ==========  ");
        analysis(search(matchPhraseQueryBuilder));
    }

    /**
     * 测试 match_phrase 和 match_phrase_prefix
     * match_phrase: 分词之后逐一搜索，返回各个结果的并集
     * match_phrase_prefix: 分词之后逐一搜索，返回各个结果的并集。与 match_phrase 不同的是，
     *      它支持最后一个词项（term）的前缀匹配（即以最后一个词项为前缀的关键字的文档）。
     */
    @Test
    public void MatchPhraseQueryWithMatchPhrasePrefixQueryTest() {
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("theDetail", "潮汕美");
        System.out.println("  ============  matchPhraseQuery  ==========  ");
        analysis(search(matchPhraseQueryBuilder));
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("theDetail", "潮汕美");
        System.out.println("  ============  matchPhrasePrefixQuery  ==========  ");
        analysis(search(matchPhrasePrefixQueryBuilder));
    }

    /**
     * 测试高亮显示
     */
    @Test
    public void highLightTest() {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("汕头");
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("name")
                .field("theDetail");
        SearchResponse searchResponse = client.prepareSearch("city")
                .setQuery(queryBuilder)
                .highlighter(highlightBuilder)
                .get();
        SearchHits hits = searchResponse.getHits();
        System.out.println("查询结果总记录数：" + hits.getTotalHits());
        System.out.println(" --- 属性列表 --- ");
        hits.forEach(hit -> {
            System.out.println(hit.getSourceAsString());
            Map<String, Object> source = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields.get("name").getFragments()[0].toString());
            System.out.println(highlightFields.get("theDetail").getFragments()[0].toString());
            System.out.println(source.get("id"));
            System.out.println(source.get("name"));
            System.out.println(source.get("theDetail"));
        });
    }

    /**
     * 根据条件查找索引为`city`类型为`city_search`的所有文档
     * @param queryBuilder
     * @return
     */
    private SearchResponse search(QueryBuilder queryBuilder) {
        return client.prepareSearch("city")
                .setTypes("city_search")
                .setQuery(queryBuilder)
                .get();
    }

    /**
     * 解析
     * @param searchResponse
     */
    private void analysis(SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        System.out.println("查询结果总记录数：" + hits.getTotalHits());
        System.out.println(" --- 属性列表 --- ");
        hits.iterator().forEachRemaining(hit -> {
            System.out.println(hit.getSourceAsString());
            Map<String, Object> source = hit.getSourceAsMap();
            System.out.println(source.get("id"));
            System.out.println(source.get("name"));
            System.out.println(source.get("theDetail"));
        });
    }

}
