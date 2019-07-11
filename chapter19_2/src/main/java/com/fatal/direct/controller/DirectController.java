package com.fatal.direct.controller;

import com.fatal.direct.sender.DirectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2019/7/7 0007 0:15
 */
@RestController
@RequestMapping("/direct")
public class DirectController {

    private DirectSender sender;

    @Autowired
    public DirectController(DirectSender sender) {
        this.sender = sender;
    }

    @GetMapping
    public void send() {
        sender.send();
    }

}
