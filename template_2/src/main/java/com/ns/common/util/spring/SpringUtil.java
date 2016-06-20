package com.ns.common.util.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by xuezhucao on 16/6/20.
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private Logger logger = Logger.getLogger(this.getClass());
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        logger.info("application context set: " + arg0);
        applicationContext = arg0;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

}
