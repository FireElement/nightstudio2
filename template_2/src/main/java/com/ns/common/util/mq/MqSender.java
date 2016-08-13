package com.ns.common.util.mq;

import com.ns.common.util.constant.MqConstant;
import com.ns.common.util.gson.GsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

/**
 * Created by xuezhucao on 16/8/13.
 */
public class MqSender {
    private RabbitTemplate template;

    public MqSender(RabbitTemplate rabbitTemplate) {
        this.template = rabbitTemplate;
    }

    public void send(String queueName, Map<String, Object> obj) throws Throwable {
        obj.put(MqConstant.QUEUE_KEY, queueName);
        template.convertAndSend(queueName, GsonUtil.toJson(obj));
    }
}
