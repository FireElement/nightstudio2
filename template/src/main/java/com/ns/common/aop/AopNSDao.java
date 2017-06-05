package com.ns.common.aop;

import com.ns.common.util.exception.sys.DataBaseOperationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by caoxuezhu01 on 14-9-15.
 */
@Aspect
@Component
public class AopNSDao {
    private static Log logger = LogFactory.getLog(AopNSDao.class);

    @Around("execution(* com.ns.template.dao..*(..))")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            logger.warn("", e);
            throw new DataBaseOperationException();
        }
    }
}
