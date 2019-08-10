package com.fatal.dto;

import com.fatal.entity.Customer;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Customer 数据传输对象
 * @author Fatal
 * @date 2019/8/9 0009 10:10
 */
@Data
public class CustomerDTO {

    private String id;

    private String name;

    private Date birthday;

    private Integer age;

    private String hobby;

    public static CustomerDTO of(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

}
