package com.ns.common.util.redis;

import com.ns.common.util.constant.RedisConstant;

/**
 * Created by xuezhucao on 15/7/19.
 */
public class RedisUtil {
    public static String getTokenKey(String token) {
        return String.format(RedisConstant.Key.TOKEN, token);
    }
}
