package com.fatal.dao.message;

import com.fatal.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Message 数据库访问组件
 * @author: Fatal
 * @date: 2018/11/2 0002 11:15
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
