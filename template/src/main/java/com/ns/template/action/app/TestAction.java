package com.ns.template.action.app;

import com.ns.common.util.constant.PathConstant;
import com.ns.template.biz.TestBiz;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/4/26.
 */
@RestController
@RequestMapping(PathConstant.APP_PREFIX + "/test")
public class TestAction {
    @Resource
    private TestBiz biz;

    @RequestMapping("/test")
    public Object test() throws Throwable {
        return biz.test();
    }
}
