package com.fatal.jdk_dynamic_proxy;

/**
 * 被代理对象
 * @author: Fatal
 * @date: 2018/11/12 0012 14:38
 */
public class RealSubject implements ISubject {

    @Override
    public void action() {
        System.out.println("我是被代理类，记得要执行我哦！！么么哒 ~ ~");
    }

}
