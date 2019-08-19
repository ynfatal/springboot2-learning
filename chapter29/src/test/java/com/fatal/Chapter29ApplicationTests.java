package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter29ApplicationTests {

    /**
     * 测试去重
     */
    @Test
    public void contextLoads() {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 4, 1, 1));
        List<Integer> collect = integers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
