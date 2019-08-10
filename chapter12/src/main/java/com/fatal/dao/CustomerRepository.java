package com.fatal.dao;

import com.fatal.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Customer 数据库访问层
 * @author Fatal
 * @date 2019/8/9 0009 8:20
 * @desc 继承了 MongoRepository，Out-of-the-box（开箱即用）；这个接口有很多操作，包括标准的CRUD操作（创建、查询、更新、删除）
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * 根据 name 名称查找 Customer
     * @param name
     * @return
     */
    Customer findCustomerByName(String name);

    /**
     * 根据 age 查找所有 Customer 集合
     * @param age
     * @return
     */
    List<Customer> findAllByAge(Integer age);

}
