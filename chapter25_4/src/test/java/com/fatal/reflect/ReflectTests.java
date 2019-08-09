package com.fatal.reflect;

import com.fatal.entity.Zi;
import com.fatal.utils.ReflectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Fatal
 * @date 2019/8/9 0009 7:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReflectTests {

    @Test
    public void getBeanInfo() {
        Zi zi = new Zi();
        zi.setZiName("zi");
        zi.setZiAge(18);
        zi.setFuName("fu");
        zi.setFuAge(42);
        Class<? extends Zi> ziClass = zi.getClass();
        List<String> fields = ReflectUtil.getFields(ziClass);
        /*
         * 实体属性值映射（这里没有考虑复杂类型，需要的话自行调整）
         */
        Map<String, Object> map = fields.stream()
                .collect(Collectors.toMap(Function.identity(), field -> {
                    try {
                        Method method = ziClass.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
                        return method.invoke(zi);
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }));
        map.entrySet().forEach(System.out::println);
    }

}
