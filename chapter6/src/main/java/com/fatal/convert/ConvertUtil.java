package com.fatal.convert;

import com.fatal.dto.UserDTO;
import com.fatal.entity.User;
import org.springframework.beans.BeanUtils;

/**
 * 数据传输对象转换器
 * @author: Fatal
 * @date: 2019/6/26 0026 11:39
 */
public class ConvertUtil {

    public static UserDTO convert(User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

}
