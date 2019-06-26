package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Fatal
 * @date: 2019/6/26 0026 11:34
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    private String username;

}
