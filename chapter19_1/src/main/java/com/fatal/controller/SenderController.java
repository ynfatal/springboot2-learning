package com.fatal.controller;

import com.fatal.sender.FatalSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2019/7/5 0005 16:09
 */
@RestController
@RequestMapping("/send")
public class SenderController {

    private FatalSender sender;

    @Autowired
    public SenderController(FatalSender sender) {
        this.sender = sender;
    }

    @GetMapping
    public void send() {
        // 发布消息
        sender.send();
    }

}
