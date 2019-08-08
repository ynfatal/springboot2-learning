package com.fatal.cglib_dynamic_proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义方法拦截器
 * 功能与jdk动态代理的InvocationHandler差不多。
 * 都是通过反射增强目标对象的方法
 * @author: Fatal
 * @date: 2018/11/13 0013 17:41
 */
public class MyMethodInterceptor implements MethodInterceptor {

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
         * Class type: 被代理类的字节码对象
         * Callback callback: 自定义的MethodInterceptor实现
         */
        return Enhancer.create(this.obj.getClass(), this);
    }

    /**
     * @desc 所有生成的代理方法都调用此方法而不是原始方法。原始方法可以通过使用方法对象的普通反射调用，也可以通过使用methodproxy（更快）调用。
     * @param obj 增强的对象
     * @param method 方法对象
     * @param args 参数
     * @param methodProxy 用于调用父类（未拦截的）方法，可根据需要多次调用
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {

        // 对参数`args`进行处理
        System.out.println("========    已对参数进行处理    ========");

        Object invoke = null;
        try {
            System.out.println("before in cglib proxy");
            /**
             * invokeSuper(Object obj, Object[] args)
             * Object obj:被代理对象
             * Object... args:实际方法参数
             */
            // 使用 MethodProxy
            invoke = methodProxy.invokeSuper(obj, args);
            // 使用方法对象的普通反射调用
//            invoke = method.invoke(this.obj, args);
        } catch (InvocationTargetException e) {
            // 注意：代理类不会改变被代理类的方法。如果代理类有异常抛出，那么被代理类也要抛出。
            throw e;
        } finally {
            System.out.println("after in cglib proxy");
        }
        // 对返回结果`invoke`进行处理
        System.out.println("========    已对返回结果进行处理    ========");

        return invoke;
    }

}
