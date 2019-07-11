package com.fatal.topic.controller;

import com.fatal.topic.sender.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Fatal
 * @date: 2019/7/6 0006 8:55
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    private TopicSender sender;

    @Autowired
    public TopicController(TopicSender sender) {
        this.sender = sender;
    }

    @GetMapping
    public void send() {
        sender.send();
    }

}
