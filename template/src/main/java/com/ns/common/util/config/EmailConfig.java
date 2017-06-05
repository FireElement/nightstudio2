package com.ns.common.util.config;

import com.ns.common.biz.EmailBiz;
import com.ns.common.biz.ParamBiz;
import com.ns.common.dao.EmailTemplateDao;
import com.ns.common.mgr.EmailTemplateMgr;
import com.ns.common.util.mq.MqSender;
import com.ns.common.util.sms.ISmsSender;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuezhucao on 16/7/27.
 */
//@Configuration
public class EmailConfig {
    @Bean
    EmailTemplateMgr emailTemplateMgr(EmailTemplateDao emailTemplateDao) {
        return new EmailTemplateMgr(emailTemplateDao);
    }

    @Bean
    EmailBiz emailBiz(EmailTemplateMgr emailTemplateMgr, ParamBiz paramBiz,
                      MqSender mqSender, ISmsSender smsSender) {
        return new EmailBiz(emailTemplateMgr, paramBiz, mqSender, smsSender);
    }
}
