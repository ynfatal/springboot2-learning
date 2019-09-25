package com.fatal.template;

import com.fatal.Chapter32ApplicationTests;
import com.fatal.entity.City;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/9/24 0024 8:40
 */
public class ElasticsearchTemplateTests extends Chapter32ApplicationTests {

    @Autowired
    private ElasticsearchTemplate template;

    /**
     * 新增文档
     */
    @Test
    public void indexTest() {
        City city = new City()
                .setName("惠州")
                .setTheDetail("惠州文化");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(city)
                .build();
        String documentId = template.index(indexQuery);
        System.out.println(documentId);
    }

    @Test
    public void bulkIndexTest() {
        List<IndexQuery> queries = Arrays.asList(
                new IndexQueryBuilder()
                        .withObject(new City().setName("重庆").setTheDetail("重庆文化"))
                        .build(),
                new IndexQueryBuilder()
                        .withObject(new City().setName("长沙").setTheDetail("长沙文化"))
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

    @Test
    public void highLightTest() {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", "汕头");
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("name")
                .preTags("<h2>")
                .postTags("</h2>");
        List<HighlightBuilder.Field> fields = highlightBuilder.fields();
        HighlightBuilder.Field[] fieldArray = fields.toArray(new HighlightBuilder.Field[1]);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightBuilder(highlightBuilder)
                .withHighlightFields(fieldArray)
                .build();
        Page<City> page = template.queryForPage(searchQuery, City.class);
        page.forEach(System.out::println);
    }

}
