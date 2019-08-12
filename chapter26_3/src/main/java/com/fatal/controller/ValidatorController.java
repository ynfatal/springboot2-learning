package com.fatal.controller;

import com.fatal.entity.Book;
import com.fatal.groups.Groups;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2018/11/28 0028 15:27
 */
@RestController
public class ValidatorController {

    @PostMapping(value = "/insert")
    public String insert(@Validated(value = Groups.Insert.class) Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }

    @PostMapping(value = "/update")
    public String update(@Validated(value = Groups.Update.class) Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }

    @PostMapping(value = "/other")
    public String other(@Validated(value = Groups.Other.class) Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }

}
