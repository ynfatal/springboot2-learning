package com.fatal.controller;

import com.fatal.service.IBusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fatal
 * @date 2019/8/29 0029 17:07
 */
@RestController
public class OversoldController {

    private IBusinessService businessService;

    public OversoldController(IBusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("/withoutLock")
    public void withoutLock() {
        businessService.businessWithoutLock();
    }

    @GetMapping("/withLock")
    public void withLock() {
        businessService.businessWithLock();
    }

    @GetMapping("/query")
    public String query() {
        return "剩余库存：" + businessService.getStock().get();
    }

}
