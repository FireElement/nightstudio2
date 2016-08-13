package com.ns.common.util.mq;

import java.util.Map;

/**
 * Created by xuezhucao on 16/4/21.
 */
public interface IMqReceiver {
    String getQueue();
    void receive(Map<String, Object> obj);
}
