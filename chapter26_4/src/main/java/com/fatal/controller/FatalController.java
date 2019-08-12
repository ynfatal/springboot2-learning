package com.fatal.controller;

import com.fatal.entity.Book;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fatal
 * @date 2019/8/12 0012 10:50
 */
@RestController
public class FatalController {

    @PostMapping
    public String combination(@Validated Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }

}
