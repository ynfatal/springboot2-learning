package com.fatal;

import com.fatal.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/9 0009 13:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MongoTemplateTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Customer customer;

    private List<Customer> customers;

    @Before
    public void before() {
        /*
         * TODO: 这里的id自己手动添加，为了后面聚合测试做区分。
         *  如果使用了数据库默认生成的id，会因为它是ObjectId("...")修饰了，而无法与外键值建立关联。
         */
        customer = new Customer()
                .setId("137063")
                .setName("fatal")
                .setAge(18)
                .setPhone("13750411111")
                .setBirthday(new Date())
                .setHobby("swim");
        customers = new ArrayList<>(Arrays.asList(
                new Customer()
                        .setName("micai")
                        .setAge(18)
                        .setPhone("13750411111")
                        .setBirthday(new Date())
                        .setHobby("swim eat"),
                new Customer()
                        .setName("xiaomi")
                        .setAge(22)
                        .setPhone("13750411111")
                        .setBirthday(new Date(System.currentTimeMillis() + 1000))
                        .setHobby("swim eat"),
                new Customer()
                        .setName("miqi")
                        .setAge(18)
                        .setPhone("13750411111")
                        .setBirthday(new Date(System.currentTimeMillis() + 2000))
                        .setHobby("swim eat")
        ));
    }

    @Test
    public void saveTest() {
        mongoTemplate.save(customer);
    }

    @Test
    public void updateById() {
        Query query = new Query(Criteria.where("id").is("137063"));
        Update update = new Update().set("name", "fatal1");
        mongoTemplate.updateFirst(query, update, Customer.class);
    }

    @Test
    public void findByQueryTest() {
        Query query = new Query(Criteria.where("id").is("137063")
            .and("name").is("fatal1"));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        customers.forEach(System.out::println);
    }

    @Test
    public void saveAllTest() {
        mongoTemplate.insertAll(customers);
    }
}
