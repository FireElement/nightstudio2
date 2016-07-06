package com.ns.common.util.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by xuezhucao on 16/6/16.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    //@Scheduled(cron = "0/20 * * * * ?")
    public void scheduler() {
        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
