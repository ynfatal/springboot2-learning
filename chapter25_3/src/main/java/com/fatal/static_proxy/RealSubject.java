package com.fatal.static_proxy;

/**
 * 被代理类
 * @author: Fatal
 * @date: 2018/11/12 0012 11:11
 */
public class RealSubject implements ISubject {

    @Override
    public void request() {
        System.out.println("被代理类RealSubject被访问了(需要增强)");
    }

    @Override
    public void hello() {
        System.out.println("被代理类RealSubject被访问了(不需要增强)");
    }

}
