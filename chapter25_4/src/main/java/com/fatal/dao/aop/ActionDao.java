package com.fatal.dao.aop;

import com.fatal.entity.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Fatal
 * @date: 2018/11/15 0015 11:33
 */
public interface ActionDao extends MongoRepository<Action, Long> {

}
