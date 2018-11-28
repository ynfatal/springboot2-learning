package com.fatal.controller;

import com.fatal.entity.Book;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 数据验证控制层
 * @author: Fatal
 * @date: 2018/11/27 0027 17:10
 */
@Validated // 开启属性参数验证
@RestController
public class ValidatorController {

    /**
     * 测试属性参数验证
     */
    @GetMapping("/test1")
    public String test1(@Validated @NotBlank(message = "name 不能为空")
                            @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间")
                                    String name) {
        /**
         * @Validated 标注在类上与 @Length... 这些注解一起校验方法参数时。
         * 这时候`BindingResult`不能起作用，
         * 错误信息返回JSON中取错误信息。
         * {
         *   "timestamp": "2018-11-27T10:13:05.743+0000",
         *   "status": 500,
         *   "error": "Internal Server Error",
         *   "message": "test1.name: name 不能为空",
         *   "path": "/test1"
         * }
         */
        return "success";
    }

    /**
     * 测试对象参数验证和嵌套验证
     */
    @PostMapping(value = "/test2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test2(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }

}
