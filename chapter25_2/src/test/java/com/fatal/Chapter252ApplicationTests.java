package com.fatal;

import com.fatal.entity.Product;
import com.fatal.log.LogService1;
import com.fatal.log.LogService2;
import com.fatal.service.ProductService;
import com.fatal.service.sub.SubService;
import com.fatal.service.sub.next.SubTowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter252ApplicationTests {

    @Autowired
    ProductService productService;

    @Autowired
    SubService subService;

    @Autowired
    SubTowService subTowService;

    @Autowired
    LogService1 logService1;

    @Autowired
    LogService2 logService2;

    /**
     * 打开`PackageOrClassAspectConfig`的`@Component`
     * 测试`匹配包/类`
     */
    @Test
    public void testPackageOrClass() {
        productService.findById(1L);
        productService.findByTwoArgs(1L,"hello");
        productService.execute();
        subService.sub();
    }

    /**
     * 打开`ObjectAspectConfig`的`@Component`
     * 测试`匹配对象`
     */
    @Test
    public void testThisAndTarget() {
        logService1.test();
        logService2.test();
        productService.execute();
        subService.sub();
    }

    /**
     * 打开`ArgsAspectConfig`的`@Component`
     * 测试`匹配参数`
     */
    @Test
    public void testArgs() {
        productService.findById(1L);
        productService.findByTwoArgs(1L,"hello");
        productService.execute();
        subService.sub();
    }

    /**
     * 打开`AnnotationAspectConfig`的`@Component`
     * 测试`匹配注解`
     */
    @Test
    public void testAnnotation() {
        productService.findById(1L);
        productService.findByTwoArgs(1L,"hello");
        productService.execute();
        subService.sub();
        logService1.test();
        logService2.test();
        productService.annoArgs(new Product());
    }

    /**
     * 打开`ExecutionAspectConfig`的`@Component`
     * 测试`匹配方法`
     */
    @Test
    public void testExecution() {
        productService.findById(1L);
        productService.findByTwoArgs(1L,"hello");
        productService.execute();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
        }
        subService.sub();
        subTowService.subTwo();
    }

    @Test
    public void testAdvice() {
        productService.execute();
        subService.sub();
        subTowService.subTwo();
        try {
            productService.exDemo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
