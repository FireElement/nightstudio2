package com.ns.common.action.common;

import com.ns.common.util.constant.PathConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caoxuezhu01 on 14-9-13.
 */
@RestController
@RequestMapping(PathConstant.COMMON_PREFIX + "/sig")
public class SigAction {

    @RequestMapping("/invalidSig")
    public Object invalidSig() throws Throwable {
        throw new NSException(ErrorCode.INVALID_SIG);
    }
}
