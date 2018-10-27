package com.fatal.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务组件
 * @author: Fatal
 * @date: 2018/10/27 0027 16:47
 */
@Slf4j
@Component
public class ScheduledTasks {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 5000)
//    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled1() throws Exception {
        log.info("scheduled1 现在时间：" + format.format(new Date()));
    }

}
