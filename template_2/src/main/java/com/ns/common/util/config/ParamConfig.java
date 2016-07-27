package com.ns.common.util.config;

import com.ns.common.biz.ParamBiz;
import com.ns.common.dao.ParamDao;
import com.ns.common.mgr.ParamMgr;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuezhucao on 16/7/27.
 */
@Configuration
public class ParamConfig {
    @Bean
    public ParamMgr paramMgr(ParamDao paramDao) {
        return new ParamMgr(paramDao);
    }

    @Bean
    public ParamBiz paramBiz(ParamMgr paramMgr) {
        return new ParamBiz(paramMgr);
    }
}
