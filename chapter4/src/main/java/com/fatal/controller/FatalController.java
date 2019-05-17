package com.fatal.controller;

import com.fatal.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Fatal
 * @date: 2018/9/26 0026 17:00
 */
@Controller
public class FatalController {

    private Random random = new Random();

    @GetMapping("/hello")
    public String success(Model model) {
        model.addAttribute("hello", "Hello Wolrd! thymeleaf渲染成功！");
        return "hello";
    }

    /**
     * 测试行内表达式
     */
    @GetMapping("/inlining")
    public String inlining(Model model) {
        model.addAttribute("hello", "<h2>hello</h2>");
        return "inlining";
    }

    /**
     * 测试 *{}
     */
    @GetMapping("/test1")
    public String test1(Model model) {
        model.addAttribute("user", new User().setName("米彩").setAge(18));
        return "test1";
    }

    /**
     * 测试 #{}
     */
    @GetMapping("/test2")
    public String test2(Model model) {
        return "test2";
    }

    /**
     * 测试 @{}
     */
    @GetMapping("/test3")
    public String test3(Model model) {
        return "test3";
    }

    /**
     * 测试 ~{} （选择器和片段名）
     */
    @GetMapping("/test4")
    public String test4(Model model) {
        return "test4";
    }

    /**
     * 测试 ~{} （th:insert，th:replace， th:include）
     */
    @GetMapping("/test5")
    public String test5(Model model) {
        return "test5";
    }

    /**
     * 测试 th:each
     */
    @GetMapping("/test6")
    public String test6(Model model) {
        List<String> strings = Arrays.asList("小米", "米彩", "米琪", "米澜");
        Map<Integer ,String> collect = strings.stream()
                .collect(Collectors.toMap(e -> random.nextInt(100), Function.identity()));
        model.addAttribute("users",collect);
        return "test6";
    }

    /**
     * 测试 th:if
     */
    @GetMapping("/test7")
    public String test7(Model model) {
        model.addAttribute("age",18);
        return "test7";
    }

}
