package com.ns.common.util.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by xuezhucao on 16/6/16.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduler() throws Throwable {
//        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
