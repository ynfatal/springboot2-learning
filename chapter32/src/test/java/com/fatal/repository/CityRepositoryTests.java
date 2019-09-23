package com.fatal.repository;

import com.fatal.Chapter32ApplicationTests;
import com.fatal.entity.City;
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
        City city = repository.save(new City()
                .setId("1")
                .setName("北京")
                .setCulture("帝都文化"));
        System.out.println(city);
    }

    @Test
    public void findByIdTest() {
        City city = repository.findById("YWT1WG0BufqMHslA0-j1")
                .orElseThrow(RuntimeException::new);
        System.out.println(city);
    }

    @Test
    public void updateTest() {
        City find = repository.findById("NGS-WG0BufqMHslAO-iw")
                .orElseThrow(RuntimeException::new);
        City city = repository.save(find.setCulture("帝都文化~~"));
        System.out.println(city);
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById("NGS-WG0BufqMHslAO-iw");
    }

    @Test
    public void deleteAllTest() {
        repository.deleteAll();
    }

    @Test
    public void saveAllTest() {
        List<City> cities = Arrays.asList(
                new City().setName("天津").setCulture("天津文化"),
                new City().setName("汕头").setCulture("汕头文化 潮汕美食，牛肉火锅"),
                new City().setName("上海").setCulture("上海文化"),
                new City().setName("深圳").setCulture("深圳文化"),
                new City().setName("广州").setCulture("广州文化，与上海的差异很大")
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
    public void findByCultureTest() {
        City city = repository.findByCulture("美");
        System.out.println(city);
    }

}