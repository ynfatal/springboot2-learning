package com.fatal.static_proxy;

/**
 * 测试客户端
 * @author: Fatal
 * @date: 2018/11/12 0012 11:17
 */
public class Client {

    public static void main(String[] args) {
        ISubject subject = new Proxy(new RealSubject());
        subject.request();
        System.out.println();
        subject.hello();
    }

}
