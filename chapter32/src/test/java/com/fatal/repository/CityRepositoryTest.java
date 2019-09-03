package com.fatal.repository;

import com.fatal.Chapter32ApplicationTests;
import com.fatal.entity.City;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fatal
 * @date 2019/9/3 0003 18:47
 */
public class CityRepositoryTest extends Chapter32ApplicationTests {

    @Autowired
    private CityRepository repository;

    @Test
    public void saveTest() {
        City city = repository.save(new City()
                .setId(1L)
                .setName("北京")
                .setCulture("帝都文化"));
        System.out.println(city);
    }

    @Test
    public void findByIdTest() {
        City city = repository.findById(1L)
                .orElseThrow(RuntimeException::new);
        System.out.println(city);
    }

    @Test
    public void updateTest() {
        City find = repository.findById(1L)
                .orElseThrow(RuntimeException::new);
        City city = repository.save(find.setCulture("帝都文化~~"));
        System.out.println(city);
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById(1L);
    }

}