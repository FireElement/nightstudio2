package com.ns.common.util.sms;

/**
 * Created by xuezhucao on 15/9/7.
 */
public interface ISmsSender {
    void send(String mobile, String content) throws Throwable;
    void send(String[] mobiles, String content) throws Throwable;
}
