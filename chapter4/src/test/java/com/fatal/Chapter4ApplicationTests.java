package com.fatal;

import com.fatal.compoment.MyLocaleResolver;
import com.fatal.config.MyMvcConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter4ApplicationTests {

    private Random random = new Random();

    @Test
    public void contextLoads() {
        List<String> strings = Arrays.asList("小米", "米彩", "米琪", "米澜");
        Map<String, Integer> collect = strings.stream()
                .collect(Collectors.toMap(Function.identity(), e -> random.nextInt()));
        System.out.println(collect);
    }

}
