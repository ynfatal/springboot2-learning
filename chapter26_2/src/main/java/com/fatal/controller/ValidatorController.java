package com.fatal.controller;

import com.fatal.entity.Student;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2018/11/28 0028 11:38
 */
@RestController
public class ValidatorController {

    @GetMapping("/")
    public String validate(@Validated Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }

        return "success";
    }

}
