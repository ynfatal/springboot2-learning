package com.fatal.cglib_dynamic_proxy;

import com.fatal.jdk_dynamic_proxy.ISubject;
import com.fatal.jdk_dynamic_proxy.RealSubject;

/**
 * @author: Fatal
 * @date: 2018/11/12 0012 15:20
 */
public class Client {

    public static void main(String[] args) {

        /** 创建被代理对象 */
        RealSubject realSubject = new RealSubject();
        /** 创建 MethodInterceptor 对象 */
        MyMethodInterceptor interceptor = new MyMethodInterceptor();

        /** 将被代理对象绑定到 InvocationHandler，并获得一个代理对象 */
        Object binding = interceptor.binding(realSubject);

        ISubject proxy = (ISubject) binding;

        /** 调用被增强的方法 */
        proxy.action();

    }

}
