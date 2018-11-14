package com.fatal.static_proxy;

/**
 * @author: Fatal
 * @date: 2018/11/12 0012 11:10
 */
public interface ISubject {

   /** 增强 */
   void request();

   /** 不增强 */
   void hello();
}
