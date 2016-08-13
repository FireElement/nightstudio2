package com.ns.common.util.config;

import com.ns.common.biz.MqBiz;
import com.ns.common.dao.MongoTestDao;
import com.ns.common.dao.MongoTestMongoDao;
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
    private MqBiz mqBiz;
    @Resource
    private MongoTestDao mongoTestDao;
    @Resource
    private MongoTestMongoDao mongoTestMongoDao;

    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduler() throws Throwable {
        /*mongoTestDao.deleteAll();

        mongoTestMongoDao.insert(new MongoTest("Smith", "Alice"));

        mongoTestDao.save(new MongoTest("Smith", "Alice"));
        mongoTestDao.save(new MongoTest("Smith", "Bob"));

        for (MongoTest mongoTest : mongoTestDao.findByFirstName("Smith")) {
            System.out.println(mongoTest);
        }*/

//        mqBiz.send();
//        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
