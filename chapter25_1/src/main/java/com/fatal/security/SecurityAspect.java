package com.fatal.security;

import com.fatal.hodler.CurrentUserHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 切面
 * @author: Fatal
 * @date: 2018/11/8 0008 11:35
 */
@Aspect     // 标注该类是切面类
@Component
public class SecurityAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly() {
    }

    /**
     * 前置通知
     */
    @Before("adminOnly()")
    public void check() {
        String user = CurrentUserHolder.get();
        if (!StringUtils.isEmpty(user)) {
            if (!"admin".equals(user)) {
                throw new RuntimeException("operation not allow");
            }
        }
    }

}