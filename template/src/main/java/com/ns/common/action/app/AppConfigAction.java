package com.ns.common.action.app;

import com.ns.common.biz.ConfigBiz;
import com.ns.common.util.constant.PathConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(PathConstant.APP_PREFIX + "/config")
public class AppConfigAction {
    @Resource
    private ConfigBiz configBiz;

    @RequestMapping("/getLastVersion")
    public Object getLastVersion() throws Throwable {
        return configBiz.getLastVersion();
    }

    @RequestMapping("/getLastConfig")
    public Object getLastConfig(@RequestParam String clientVersion) throws Throwable {
        return configBiz.getLastConfig(clientVersion);
    }
}