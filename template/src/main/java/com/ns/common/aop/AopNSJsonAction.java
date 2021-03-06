package com.ns.common.aop;

import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.SystemInternalException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxz on 6/19/14.
 */
@Aspect
@Component
public class AopNSJsonAction {
    private static Log logger = LogFactory.getLog(AopNSJsonAction.class);

    private final static String el = "execution(* com.ns.template.action..*(..))";
    private final static String el1 = "execution(* com.ns.common.action..*(..))";

    @Around(el)
    public Object around(ProceedingJoinPoint joinPoint) {
        return handle(joinPoint);
    }

    @Around(el1)
    public Object around1(ProceedingJoinPoint joinPoint) {
        return handle(joinPoint);
    }

    private Object handle(ProceedingJoinPoint joinPoint) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        int error = 0;
        String msg;
        Object data;
        try {
            data = joinPoint.proceed();
        } catch (Throwable e) {
            logger.warn("", e);
            NSException e1;
            if (e instanceof NSException) {
                e1 = (NSException) e;
                error = e1.getCode();
                msg = e1.getMessage();
            } else if (StringUtils.isNotEmpty(e.getMessage())) {
                e1 = new NSException(ErrorCode.UNKOWN_EXCEPTION);
                error = e1.getCode();
                msg = e.getMessage();
            } else {
                e1 = new SystemInternalException();
                error = e1.getCode();
                msg = e1.getMessage();
            }
            Map<String, String> map = new HashMap<>(1);
            map.put("msg", msg);
            data = map;
        }
        result.put("error", error);
        result.put("data", data);
        return result;
    }
}
