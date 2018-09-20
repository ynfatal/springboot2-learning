package com.fatal.controller;

import com.fatal.config.Fatal2;
import com.fatal.config.Fatal1;
import com.fatal.config.Fatal3;
import com.fatal.config.Fatal4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Fatal
 * @date: 2018/9/20 0020 16:21
 */
@RestController
public class FatalController {

    @Autowired
    private Fatal1 fatal1;

    @Autowired
    private Fatal2 fatal2;

    @Autowired
    private Fatal3 fatal3;

    @Autowired
    private Fatal4 fatal4;

    /**
     * 测试 @Value
     */
    @RequestMapping("/getPropertyValue1")
    public Map<String, Object> showComponentProperty1() {
        Map<String, Object> map = new HashMap<>();
        map.put("注解","@Value");
        map.put("name", fatal1.getName());
        map.put("age", fatal1.getAge());
        return map;
    }

    /**
     * 测试 @ConfigurationProperties
     */
    @RequestMapping("/getPropertyValue2")
    public Map<String, Object> showComponentProperty2() {
        Map<String, Object> map = new HashMap<>();
        map.put("注解","@ConfigurationProperties");
        map.put("name", fatal2.getName());
        map.put("age", fatal2.getAge());
        return map;
    }

    /**
     * 测试 @ConfigurationProperties + @Validated
     */
    @RequestMapping("/getPropertyValue3")
    public Map<String, Object> showComponentPropertyWithValidated() {
        Map<String, Object> map = new HashMap<>();
        map.put("注解","@ConfigurationProperties + @Validated");
        map.put("name", fatal3.getName());
        map.put("age", fatal3.getAge());
        map.put("email", fatal3.getEmail());
        return map;
    }

    /**
     * 测试 @PropertySource
     */
    @RequestMapping("/getPropertyValue4")
    public Map<String, Object> showComponentProperty3() {
        Map<String, Object> map = new HashMap<>();
        map.put("注解","@PropertySource");
        map.put("name", fatal4.getName());
        map.put("age", fatal4.getAge());
        return map;
    }

}
