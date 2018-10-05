package com.fatal.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author: Fatal
 * @date: 2018/10/5 0005 17:55
 */
public interface AccountMapper {

    Integer update(@Param("money") Double money, @Param("id") Integer id);

}
