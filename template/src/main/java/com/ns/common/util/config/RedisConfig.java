package com.ns.common.util.config;

import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by xuezhucao on 16/6/28.
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory(ParamBiz paramBiz) throws Throwable {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(paramBiz.getStringByName(ParamConstant.Key.REDIS_SERVER_1_IP));
        factory.setPort(paramBiz.getIntByName(ParamConstant.Key.REDIS_SERVER_1_PORT));
        factory.setPassword(paramBiz.getStringByName(ParamConstant.Key.REDIS_SERVER_1_PASSWD));
        return factory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new StringRedisTemplate(factory);
        template.afterPropertiesSet();
        return template;
    }
}
