package com.fatal.controller;

import com.fatal.annotation.LocalLock;
import com.fatal.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2018/11/29 0029 9:43
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    // 因为重复提交的数据相同，所以这里用请求数据作为key
    @LocalLock(key = "book:arg[0]")
    @PostMapping("/insert")
    public String insert(@Validated @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        book.setId(System.currentTimeMillis());
        return "提交成功 --> [{" + book + "}]";
    }

}
