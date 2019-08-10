package com.fatal;

import com.fatal.dao.CustomerRepository;
import com.fatal.dto.CustomerDTO;
import com.fatal.entity.Customer;
import com.fatal.enums.OperationEnum;
import com.fatal.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/9 0009 12:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SingleTableTests {

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    private List<Customer> customers;

    @Before
    public void before() {
        customer = new Customer()
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
        Customer save = repository.save(customer);
        log.info("【新增 Customer】 [status = {}, entity = {}]",
                OperationEnum.SUCCESS.getCode(), save);
    }

    @Test
    public void updateTest() {
        Customer customer = repository.findById("5d4e8ddbe429c6d1303ad1ad")
                .orElseThrow(RuntimeException::new);
        Customer save = customer.setHobby("eat");
        repository.save(customer);
        log.info("【更新 Customer】 [status = {}, entity = {}]",
                OperationEnum.SUCCESS.getCode(), save);
    }

    @Test
    public void findByIdTest() {
        Customer customer = repository.findById("5d4e8ddbe429c6d1303ad1ad")
                .orElseThrow(RuntimeException::new);
        log.info("【查询 Customer】 findById [status = {}, entity = {}]",
                OperationEnum.SUCCESS.getCode(), customer);
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById("5d4e8ddbe429c6d1303ad1ad");
        log.info("【删除 Customer】 [status = {}, id = {}]",
                OperationEnum.SUCCESS.getCode(), "5d4e8ddbe429c6d1303ad1ad");
    }

    @Test
    public void saveAllTest() {
        repository.saveAll(customers).forEach(System.out::println);
    }

    @Test
    public void findCustomerByNameTest() {
        Customer customer = repository.findCustomerByName("micai");
        log.info("【查询 Customer】 findCustomerByName [status = {}, entity = {}]",
                OperationEnum.SUCCESS.getCode(), customer);
    }

    @Test
    public void findAllByAgeTest() {
        repository.findAllByAge(18).forEach(System.out::println);
    }

    @Test
    public void findAllByExampleTest() {
        Example<Customer> example = Example.of(new Customer().setAge(18).setName("micai"));
        repository.findAll(example).forEach(System.out::println);
    }

    @Test
    public void pageTest() {
        Example<Customer> example = Example.of(new Customer().setHobby("swim eat"));
        /*
         * 先根据年龄升序，年龄相同的根据生日降序
         */
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(
                new Sort.Order(Sort.Direction.ASC, "age"),
                new Sort.Order(Sort.Direction.DESC, "birthday")
        ));
        Page<Customer> page = repository.findAll(example, pageRequest);
        Page<CustomerDTO> dtoPage = page.map(CustomerDTO::of);
        System.out.println(JsonUtil.toJson(dtoPage));
    }

}
