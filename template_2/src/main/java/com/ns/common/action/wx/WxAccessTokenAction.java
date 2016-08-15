package com.ns.common.action.wx;

import com.ns.common.biz.WxAuthBiz;
import com.ns.common.util.constant.PathConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuezhucao on 16/4/26.
 */
@RestController
@RequestMapping(PathConstant.WX_PREFIX + "/accessToken")
public class WxAccessTokenAction {
    @Autowired
    private WxAuthBiz biz;

    @RequestMapping("/checkAndRefreshAccessToken")
    public Object checkAndRefreshAccessToken(@RequestParam String accessToken) throws Throwable {
        return biz.checkAndRefreshAccessToken(accessToken);
    }

    @RequestMapping("/createAccessToken")
    public Object createAccessToken(@RequestParam String code) throws Throwable {
        return biz.createAccessToken(code);
    }

    @RequestMapping("/loginWithAccessToken")
    public Object loginWithAccessToken(@RequestParam String accessToken) throws Throwable {
        return biz.loginWithAccessToken(accessToken);
    }
}
