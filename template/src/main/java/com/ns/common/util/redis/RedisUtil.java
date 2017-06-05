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

    public static String getWxAccessTokenKey(String accessToken) {
        return String.format(RedisConstant.Key.WX_ACCESS_TOKEN, accessToken);
    }

    public static String getWxRefreshTokenKey(String accessToken) {
        return String.format(RedisConstant.Key.WX_REFRESH_TOKEN, accessToken);
    }

    public static String getWxOpenIdKey(String openId) {
        return String.format(RedisConstant.Key.WX_OPEN_ID, openId);
    }

    public static String getWxUserIdKey(long userId) {
        return String.format(RedisConstant.Key.WX_USER_ID, userId);
    }

    public static String getWxJsSdkAccessTokenKey() {
        return String.format(RedisConstant.Key.WX_JS_SDK_ACCESS_TOKEN);
    }

    public static String getWxJsSdkTicketKey() {
        return RedisConstant.Key.WX_JS_SDK_TICKET;
    }
}
