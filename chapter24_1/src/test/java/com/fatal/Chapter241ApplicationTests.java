package com.fatal;

import com.fatal.component.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter241ApplicationTests {

    @Autowired
    private Task task;

    @Test
    public void contextLoads() throws Exception {
        Future<String> taskOne = task.doTaskOne();
        Future<String> taskTwo = task.doTaskTwo();
        Future<String> taskThree = task.doTaskThree();
        long start = System.currentTimeMillis();
        while (true) {
            // 判断异步方法是否执行完成
            if (taskOne.isDone() && taskTwo.isDone() && taskThree.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }


}
