package com.ns.common.util.mq;

import com.google.gson.reflect.TypeToken;
import com.ns.common.util.constant.MqConstant;
import com.ns.common.util.gson.GsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 16/8/13.
 */
public class MqReceiverContainer {
    private static Log logger = LogFactory.getLog(MqReceiverContainer.class);
    private Map<String, IMqReceiver> receiverMap = new HashMap<>(10);

    public void handleMessage(String foo) {
        try {
            Map<String, Object> map = GsonUtil.fromJson(foo, new TypeToken<Map<String, Object>>() {});
            String queue = (String) map.get(MqConstant.QUEUE_KEY);
            IMqReceiver receiver = receiverMap.get(queue);
            if (receiver == null) {
                logger.warn("can not find receiver for queue " + queue);
                return;
            }
            receiver.receive(map);
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public void addReceiver(IMqReceiver receiver) {
        receiverMap.put(receiver.getQueue(), receiver);
    }
}
