package com.fatal.mapper;

import com.fatal.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author: Fatal
 * @date: 2018/10/3 0003 21:47
 */
@SqlResource("user")
public interface IUserMapper extends BaseMapper<User> {

    List<User> selectSample(User query);

}
