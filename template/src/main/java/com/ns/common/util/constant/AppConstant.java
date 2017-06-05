package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 16/4/26.
 */
public interface AppConstant {
    interface Platform {
        Integer ANDROID = 1;
        Integer IOS = 2;

        Integer[] ALL = {
                ANDROID,
                IOS
        };
    }

    interface AppId {
        Integer APP_1 = 1;

        Integer[] ALL = {
                APP_1
        };
    }
}
