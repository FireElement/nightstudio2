package com.ns.common.util.redis;

import com.ns.common.util.constant.RedisConstant;

/**
 * Created by xuezhucao on 15/7/19.
 */
public class RedisUtil {
    public static String getTokenKey(String token) {
        return String.format(RedisConstant.Key.TOKEN, token);
    }

    public static String getWebTokenKey(String token) {
        return String.format(RedisConstant.Key.WEB_TOKEN, token);
    }

    public static String getOpUserKey(long id) {
        return String.format(RedisConstant.Key.OP_USER, id);
    }
}
