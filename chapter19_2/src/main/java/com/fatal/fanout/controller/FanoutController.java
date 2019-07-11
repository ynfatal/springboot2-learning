package com.fatal.fanout.controller;

import com.fatal.fanout.sender.FanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2019/7/6 0006 10:45
 */
@RestController
@RequestMapping("/fanout")
public class FanoutController {

    private FanoutSender sender;

    @Autowired
    public FanoutController(FanoutSender sender) {
        this.sender = sender;
    }

    @GetMapping
    public void send() {
        // 发布
        sender.send();
    }

}
