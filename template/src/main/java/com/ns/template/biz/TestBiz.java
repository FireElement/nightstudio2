package com.ns.template.biz;

import com.ns.common.biz.ParamBiz;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/4/26.
 */
@Service
public class TestBiz {
    @Resource
    private ParamBiz paramBiz;

    public String test() throws Throwable {
        return "ok";
    }
}
