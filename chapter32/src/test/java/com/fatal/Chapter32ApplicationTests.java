package com.fatal;

import com.fatal.entity.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter32ApplicationTests {

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void contextLoads() {
        boolean isCreate = template.createIndex(City.class);
        if (isCreate) {
            System.out.println("city create success");
        }
    }

}
