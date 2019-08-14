package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Fatal
 * @date 2019/8/14 0014 10:06
 */
@Data
@Accessors(chain = true)
public class ParamDTO {

    private Long id;

    private String username;

}
