package com.fatal.mapper;

import com.fatal.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: Fatal
 * @date: 2018/9/30 0030 21:42
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 实现`JpaRepository`后有好多方法可以用

    /**
     * 自定义分页查询
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM USER",
            countQuery = "SELECT count(*) FROM USER",
            nativeQuery = true)
    Page<User> findPage(Pageable pageable);
}
