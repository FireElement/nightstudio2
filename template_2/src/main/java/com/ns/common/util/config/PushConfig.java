package com.ns.common.util.config;

import com.ns.common.biz.PushBiz;
import com.ns.common.dao.PushTemplateDao;
import com.ns.common.mgr.PushTemplateMgr;
import com.ns.common.util.push.IPushSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuezhucao on 16/7/27.
 */
@Configuration
public class PushConfig {
    @Bean
    PushTemplateMgr pushTemplateMgr(PushTemplateDao pushTemplateDao) {
        return new PushTemplateMgr(pushTemplateDao);
    }

    @Bean
    PushBiz pushBiz(PushTemplateMgr pushTemplateMgr, IPushSender sender) {
        return new PushBiz(pushTemplateMgr, sender);
    }
}
