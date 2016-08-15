package com.ns.common.action.wx;

import com.ns.common.biz.WxJsSdkBiz;
import com.ns.common.util.constant.PathConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuezhucao on 16/3/24.
 */
@RestController
@RequestMapping(PathConstant.WX_PREFIX + "/jssdk")
public class WxJsSdkAction {
    @Autowired
    private WxJsSdkBiz biz;

    @RequestMapping("/getConfig")
    public Object login(String url) throws Throwable {
        return biz.getWxConfig(url);
    }

}
