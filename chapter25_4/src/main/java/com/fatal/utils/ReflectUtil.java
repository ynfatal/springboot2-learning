package com.fatal.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fatal
 * @date 2019/8/9 0009 7:47
 */
public class ReflectUtil {

    /**
     * 属性集合（private + 继承的）
     * 适用于实体
     * @param clazz 实体字节码文件对象
     * @return
     */
    public static <T> List<String> getFields(Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Class<? super T> superclass = clazz.getSuperclass();
        Field[] fields = superclass.getDeclaredFields();
        // 过滤出 protected 属性
        List<Field> superProtectedFields = Arrays.stream(fields)
                .filter(field -> (field.getModifiers() & Modifier.PROTECTED) != 0)
                .collect(Collectors.toList());
        // 过滤出 private 属性
        List<Field> privateFields = Arrays.stream(declaredFields)
                .filter(field -> (field.getModifiers() & Modifier.PRIVATE) != 0)
                .collect(Collectors.toList());
        // 子类属性集合（private + 继承的）
        privateFields.addAll(superProtectedFields);
        return privateFields.stream()
                .map(Field::getName)
                .collect(Collectors.toList());
    }

}
