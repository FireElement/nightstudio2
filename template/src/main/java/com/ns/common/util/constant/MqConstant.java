package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 16/4/21.
 */
public interface MqConstant {
    String QUEUE_KEY = "queue";

    interface Queue {
        String SMS = "SMS";
        String EMAIL = "EMAIL";

        String[] ALL = {
                SMS,
                EMAIL
        };
    }
}
