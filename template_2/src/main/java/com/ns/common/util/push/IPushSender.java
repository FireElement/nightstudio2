package com.ns.common.util.push;

import java.util.Map;

/**
 * Created by xuezhucao on 15/9/10.
 */
public interface IPushSender {
    void push2Driver(long userId, long type, String msg, String page, Map<String, String> params) throws Throwable;
}
