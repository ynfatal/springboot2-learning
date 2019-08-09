package com.fatal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fatal
 * @date 2019/8/8 0008 17:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Zi extends Fu {

    public String publicZi;

    private String ziName;

    private Integer ziAge;

}
