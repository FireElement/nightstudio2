package com.ns.common.util.push;

import java.util.List;
import java.util.Map;

/**
 * Created by xuezhucao on 15/9/10.
 */
public interface IPushSender {
    void push2Client1(long userId, long type, String msg, String page, Map<String, String> params) throws Throwable;
    void push2Client1(List<Long> userIds, long type, String msg, String page, Map<String, String> params) throws Throwable;
}
