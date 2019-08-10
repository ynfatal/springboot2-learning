package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * `客户详细信息`数据传输对象
 * @author Fatal
 * @date 2019/8/9 0009 18:31
 */
@Data
@Accessors(chain = true)
public class CustomerDetailDTO {

    private String id;

    private String name;

    private Date birthday;

    private Integer age;

    private String hobby;

    private String phone;

    private List<AddressDTO> addressList;

}
