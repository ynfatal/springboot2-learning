package com.fatal.static_proxy;

/**
 * 代理类
 * @author: Fatal
 * @date: 2018/11/12 0012 11:12
 */
public class Proxy implements ISubject {

    /** 被代理对象 */
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("======  before  ======");
        try {
            realSubject.request();
        } catch (Exception e) {
            // 注意：代理类不会改变被代理类的方法。如果代理类有异常抛出，那么被代理类也要抛出。
            throw e;
        } finally {
            System.out.println("======  after  ======");
        }
    }

    @Override
    public void hello() {
        realSubject.hello();
    }

}
