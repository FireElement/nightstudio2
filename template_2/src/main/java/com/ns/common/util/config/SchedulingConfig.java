package com.ns.common.util.config;

import com.ns.common.biz.MqBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by xuezhucao on 16/6/16.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    private MqBiz mqBiz;

    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduler() throws Throwable {
        mqBiz.send();
        //System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
