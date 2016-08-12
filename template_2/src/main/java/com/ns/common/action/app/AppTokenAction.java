package com.ns.common.action.app;

import com.ns.common.biz.TokenBiz;
import com.ns.common.util.constant.PathConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by caoxuezhu01 on 14-9-13.
 */
@RestController
@RequestMapping(PathConstant.APP_PREFIX + "/token")
public class AppTokenAction {
    @Resource
    protected TokenBiz biz;

    @RequestMapping("/invalidToken.do")
    public Object invalidToken() throws Throwable {
        throw new NSException(ErrorCode.INVALID_TOKEN);
    }

    @RequestMapping("/checkToken.do")
    public Object check(@RequestParam String token) throws Throwable {
        return biz.check(token);
    }

}
