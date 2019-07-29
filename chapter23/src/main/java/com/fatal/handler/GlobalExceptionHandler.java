package com.fatal.handler;

import com.fatal.exception.AdaptiveException;
import com.fatal.exception.TraditionalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义全局异常处理器
 * 子类的处理方法优先于父类获得处理权，若子类不存在，就近原则，离子类最近的父类的处理方法获得处理权
 * @author: Fatal
 * @date: 2018/10/30 0030 14:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** 状态码键名 */
    private final String CUSTOM_STATUS_CODE = "javax.servlet.error.status_code";

    /**
     * 不自适应
     */
    @ResponseBody
    @ExceptionHandler(TraditionalException.class)
    public Map<String, Object> fatal(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "traditional.code");
        map.put("message", e.getMessage());
        return map;
    }

    /**
     * 自适应
     */
    @ExceptionHandler(AdaptiveException.class)
    public String adaptive(Exception e, HttpServletRequest request) {
        // 传入自己的错误状态码，如 4xx,5xx 不传默认200
        request.setAttribute(CUSTOM_STATUS_CODE, 500);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "adaptive.code");
        map.put("message", e.getMessage());
        request.setAttribute("ext", map);
        return "forward:/error";
    }

}
