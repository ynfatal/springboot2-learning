package com.fatal.jdk_dynamic_proxy;

/**
 * @author: Fatal
 * @date: 2018/11/12 0012 15:20
 */
public class Client {

    public static void main(String[] args) {
        // 添加这个参数，会保存 jdk 代理生成的 class 文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        /** 创建被代理对象 */
        RealSubject realSubject = new RealSubject();
        /** 创建 InvocationHandler 对象 */
        MyInvocationHandler handler = new MyInvocationHandler();

        /** 将被代理对象绑定到 InvocationHandler，并获得一个代理对象 */
        Object binding = handler.binding(realSubject);

        ISubject proxy = (ISubject) binding;

        /** 调用被增强的方法 */
        proxy.action();

    }

}
