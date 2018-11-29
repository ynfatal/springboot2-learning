package com.fatal.handler;

import com.fatal.annotation.LocalLock;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 本地锁处理器，后续讲解redis方案
 * @author: Fatal
 * @date: 2018/11/29 0029 10:43
 */
@Aspect
@Component
public class LockMethodHandler {

    private static final Cache<String, Object> CACHE = CacheBuilder.newBuilder()
            // 最大缓存 1000 个
            .maximumSize(1000)
            // 这里设置为 5 秒过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    // 项目中取获得用户名（唯一的）
    private static final String USERNAME = "fatal";

    /**
     * 切点：标注有 @LocalLock 的所有方法
     */
    @Pointcut("execution(public * *(..)) && @annotation(com.fatal.annotation.LocalLock)")
    public void point() {}

    @Around("point()")
    public Object handler(ProceedingJoinPoint pjp) {
        // 获得方法署名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获得目标方法
        Method method = signature.getMethod();
        // 获得方法上的注解 @LocalLock
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        // 获得生成的key
        String key = getKey(localLock.key(), pjp.getArgs());

        if (!StringUtils.isEmpty(key)) {
            if (CACHE.getIfPresent(key) != null) {
                throw new RuntimeException("请勿重复提交");
            }
            // 如果是第一次提交，就将 key 与当前用户名放到缓存中
            CACHE.put(key, USERNAME);
        }

        try {
            // 执行目标方法
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        } finally {
            // TODO 这里为了验证效果。模拟目标方法还没执行完毕，就把释放锁给注掉
            // 执行成功后释放锁（把key对应的缓存值清除）
//            CACHE.invalidate(key);
        }

    }

    /**
     * key的生成形式，如果想灵活点可以写成接口与实现类的方式（TODO 后面会讲解）
     * @param keyExpress key表达式
     * @param args 方法参数
     * @return 生成的key
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg["+ i +"]", args[i].toString());
        }
        // 生成的key后面拼上用户名以区别不同用户同一时间提交相同的数据
        keyExpress += ",UserName[" + USERNAME + "]";
        return keyExpress;
    }

}
