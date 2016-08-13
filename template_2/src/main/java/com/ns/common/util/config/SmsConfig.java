package com.ns.common.util.config;

import com.ns.common.biz.SmsBiz;
import com.ns.common.dao.SmsTemplateDao;
import com.ns.common.mgr.SmsTemplateMgr;
import com.ns.common.util.mq.MqSender;
import com.ns.common.util.sms.ISmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuezhucao on 16/7/27.
 */
@Configuration
public class SmsConfig {
    @Bean
    SmsTemplateMgr smsTemplateMgr(SmsTemplateDao smsTemplateDao) {
        return new SmsTemplateMgr(smsTemplateDao);
    }

    @Bean
    SmsBiz smsBiz(SmsTemplateMgr smsTemplateMgr, MqSender mqSender, ISmsSender smsSender) {
        return new SmsBiz(smsTemplateMgr, mqSender, smsSender);
    }
}
