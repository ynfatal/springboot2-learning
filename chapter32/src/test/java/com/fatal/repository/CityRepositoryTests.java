package com.fatal.repository;

import com.fatal.Chapter32ApplicationTests;
import com.fatal.entity.City;
import org.elasticsearch.index.query.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/9/3 0003 18:47
 */
public class CityRepositoryTests extends Chapter32ApplicationTests {

    @Autowired
    private CityRepository repository;

    @Test
    public void saveTest() {
        City city = repository.save(City.builder()
                .name("北京")
                .theDetail("帝都文化")
                .build());
        System.out.println(city);
    }

    @Test
    public void findByIdTest() {
        City city = repository.findById("1")
                .orElseThrow(RuntimeException::new);
        System.out.println(city);
    }

    @Test
    public void updateTest() {
        City find = repository.findById("1")
                .orElseThrow(RuntimeException::new);
        find.setTheDetail("帝都文化~~");
        City city = repository.save(find);
        System.out.println(city);
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById("7zyQYW0BRs1ajIug43WK");
    }

    @Test
    public void deleteAllTest() {
        repository.deleteAll();
    }

    @Test
    public void saveAllTest() {
        List<City> cities = Arrays.asList(
                City.builder().name("天津").theDetail("天津文化").build(),
                City.builder().name("汕头").theDetail("汕头文化 潮汕美食，牛肉火锅").build(),
                City.builder().name("上海").theDetail("上海文化").build(),
                City.builder().name("深圳").theDetail("深圳文化").build(),
                City.builder().name("广州").theDetail("广州文化，与上海的差异很大").build()
        );
        Iterable<City> result = repository.saveAll(cities);
        result.forEach(System.out::println);
    }

    @Test
    public void findAllTest() {
        Iterable<City> all = repository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void findByTheDetailTest() {
        City city = repository.findByTheDetail("美");
        System.out.println(city);
    }

    @Test
    public void matchQueryTest() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("theDetail", "汕头文化");
        Iterable<City> cities = repository.search(queryBuilder);
        print(cities);
    }

    @Test
    public void matchPhraseQueryTest() {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("theDetail", "汕头文化");
        Iterable<City> cities = repository.search(queryBuilder);
        print(cities);
    }

    @Test
    public void matchAllQueryTest() {
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        Iterable<City> cities = repository.search(queryBuilder);
        print(cities);
    }

    /**
     * term查询的时候不被解析，只有查询词和文档中的词精确匹配才能被查到
     */
    @Test
    public void termQueryTest() {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", "深圳");
        Iterable<City> cities = repository.search(queryBuilder);
        print(cities);
    }

    /**
     * 复合条件查询
     */
    @Test
    public void compoundConditionalQueryTest() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", "汕头"))
                .should(QueryBuilders.matchQuery("theDetail", "帝都"));
        Iterable<City> cities = repository.search(queryBuilder);
        print(cities);
    }

    private void print(Iterable<City> cities) {
        cities.forEach(System.out::println);
    }

}