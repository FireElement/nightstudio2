package com.ns.common.aop;

import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.SystemInternalException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by cxz on 6/19/14.
 */
@Aspect
@Component
public class AopNSRpcAction {
    private static Log logger = LogFactory.getLog(AopNSRpcAction.class);

//    private final static String el = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";
    private final static String el = "execution(* com.ns.template.rpc..*(..))";

    @Around(el)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return handle(joinPoint);
    }

    private Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            logger.warn("", e);
            NSException e1;
            if (e instanceof NSException) {
                e1 = (NSException) e;
            } else {
                e1 = new SystemInternalException();
            }
            throw new Exception(e1.getMessage());
        }
    }
}
