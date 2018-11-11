package com.fatal.log;

import org.springframework.stereotype.Component;

/**
 * @author: Fatal
 * @date: 2018/11/10 0010 15:11
 */
@Component
public class LogService1 implements Loggable {

    @Override
    public void test() {
        System.out.println("Loggable的子类LogService1");
    }

}
