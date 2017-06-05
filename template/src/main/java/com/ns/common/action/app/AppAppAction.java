package com.ns.common.action.app;

import com.ns.common.bean.App;
import com.ns.common.biz.AppBiz;
import com.ns.common.util.constant.PathConstant;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/4/26.
 */
@RestController
@RequestMapping(PathConstant.APP_PREFIX + "/app")
public class AppAppAction {
    @Resource
    private AppBiz appBiz;

    @RequestMapping("/getLastVersionAndDownloadUrl")
    @ResponseBody
    public Object getLastVersionAndDownloadUrl(@ModelAttribute App app) throws Throwable {
        return appBiz.getLastVersionAndDownloadUrl(app);
    }
}
