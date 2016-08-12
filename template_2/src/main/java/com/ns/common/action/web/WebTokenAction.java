package com.ns.common.action.web;

import com.ns.common.util.constant.PathConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caoxuezhu01 on 14-9-13.
 */
@RestController
@RequestMapping(PathConstant.WEB_PREFIX + "/token")
public class WebTokenAction {
    @RequestMapping("/invalidToken")
    @ResponseBody
    public Object invalidToken() throws Throwable {
        throw new NSException(ErrorCode.INVALID_TOKEN);
    }
}
