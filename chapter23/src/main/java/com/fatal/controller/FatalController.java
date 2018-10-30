package com.fatal.controller;

import com.fatal.exception.AdaptiveException;
import com.fatal.exception.TraditionalException;
import com.fatal.handler.GlobalExceptionHandler;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Fatal
 * @date: 2018/10/30 0030 14:50
 */
@Controller
@Data
public class FatalController {

    private GlobalExceptionHandler handler;

    FatalController(GlobalExceptionHandler handler) {
        this.handler = handler;
    }

    /**
     * 模拟 400 错误
     */
    @GetMapping("/400")
    public void error400 (@RequestParam(value = "data", required = true) String data) {

    }

    /**
     * 测试传统定制的json数据
     */
    @GetMapping("/traditional")
    public void errorTraditional500() {
        throw new TraditionalException();
    }

    /**
     * 测试定制的json数据（保持自适应效果）
     */
    @GetMapping("/adaptive")
    public void errorAdaptive500() {
        throw new AdaptiveException();
    }


}
