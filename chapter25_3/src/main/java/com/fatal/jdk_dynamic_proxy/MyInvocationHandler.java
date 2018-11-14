package com.fatal.jdk_dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自定义调用处理程序
 * @author: Fatal
 * @date: 2018/11/12 0012 14:39
 */
public class MyInvocationHandler implements InvocationHandler {

    /** 声明被代理对象 */
    private Object obj;

    /**
     * 初始化被代理对象
     * 动态创建代理类并返回
     * @return 代理类
     */
    public Object binding(Object obj) {
        this.obj = obj;
        /**
         * ClassLoader loader: 被代理类的类加载器
         * Class<?>[] interfaces: 被代理类实现的所有方法
         * InvocationHandler h: 调用程序处理器
         */
        return Proxy.newProxyInstance(this.obj.getClass().getClassLoader()
            ,this.obj.getClass().getInterfaces(),this);
    }

    /**
     * 书写增强逻辑
     * @param proxy
     * @param method 方法对象
     * @param args 实际方法参数
     * @return 方法返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 对参数`args`进行处理
        System.out.println("========    已对参数进行处理    ========");

        Object invoke = null;
        try {
            System.out.println("before in jdk proxy");
            /**
             * Object invoke(Object obj, Object... args)
             * Object obj:被代理对象
             * Object... args:实际方法参数
             */
            invoke = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            // 注意：代理类不会改变被代理类的方法。如果代理类有异常抛出，那么被代理类也要抛出。
            throw e;
        } finally {
            System.out.println("after in jdk proxy");
        }
        // 对返回结果`invoke`进行处理
        System.out.println("========    已对返回结果进行处理    ========");
        return invoke;
    }
}
