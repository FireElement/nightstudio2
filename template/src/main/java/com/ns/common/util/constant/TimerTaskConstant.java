package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 16/4/15.
 */
public interface TimerTaskConstant {
    int PROCESS_TASK_COUNT = 10;
    long PROCESS_TASK_SLEEP_INTERVAL = 5 * 60 * 1000;

    interface Type {
        int SEND_SMS = 1;

        Integer[] ALL = {
                SEND_SMS
        };
    }

    interface ParamMapKey {
        String MOBILE = "MOBILE";
        String TEMPLATE = "TEMPLATE";
    }
}
