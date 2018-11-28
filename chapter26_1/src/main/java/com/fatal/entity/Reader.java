package com.fatal.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author: Fatal
 * @date: 2018/11/27 0027 18:22
 */
@Data
public class Reader {

    private Long id;

    @NotBlank(message = "readerName 不能为空")
    @Length(min = 2, max = 10, message = "readerName 长度必须在 {min} - {max} 之间")
    private String readerName;

}
