package com.ns.common.util.config;

import com.ns.common.biz.ParamBiz;
import com.ns.common.biz.SmsBiz;
import com.ns.common.util.constant.MqConstant;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.mq.MqReceiverContainer;
import com.ns.common.util.mq.MqSender;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

/**
 * Created by xuezhucao on 16/8/13.
 */
//@Configuration
public class RabbitmqConfig {

    @Bean
    ConnectionFactory connectionFactory(ParamBiz paramBiz) throws Throwable {
        CachingConnectionFactory factory = new CachingConnectionFactory(paramBiz.getStringByName(ParamConstant.Key.RABBIT_HOST));
        factory.setUsername(paramBiz.getStringByName(ParamConstant.Key.RABBIT_USER_NAME));
        factory.setPassword(paramBiz.getStringByName(ParamConstant.Key.RABBIT_PASSWORD));
        return factory;
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    MqReceiverContainer mqReceiverContainer(SmsBiz smsBiz) {
        MqReceiverContainer result = new MqReceiverContainer();
        result.addReceiver(smsBiz);
        return result;
    }

    @Bean
    MqSender sender(RabbitTemplate rabbitTemplate) {
        return new MqSender(rabbitTemplate);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MqReceiverContainer mqReceiverContainer) {
        return new MessageListenerAdapter(mqReceiverContainer);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MqConstant.Queue.ALL);
        container.setMessageListener(listenerAdapter);
        return container;
    }

}
