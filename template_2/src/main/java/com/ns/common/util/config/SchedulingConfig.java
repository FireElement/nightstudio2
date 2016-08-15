package com.ns.common.util.config;

import com.ns.common.biz.MarkBiz;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/6/16.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Resource
    private MarkBiz markBiz;

    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduler() throws Throwable {
        /*Mark mark = new Mark();
        mark.setType(MarkConstant.Type.TEST);

        mark = markBiz.create(mark);

        System.out.println(mark.getId());

        mark = markBiz.getByValue(mark.getValue());

        System.out.println(mark.getValue());*/

//        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
