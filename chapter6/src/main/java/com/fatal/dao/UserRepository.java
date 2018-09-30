package com.fatal.dao;

import com.fatal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Fatal
 * @date: 2018/9/30 0030 21:42
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 实现`JpaRepository`后有好多方法可以用
}
