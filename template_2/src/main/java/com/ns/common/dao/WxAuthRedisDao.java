package com.ns.common.dao;

import com.ns.common.dao.spi.redis.AbsNSRedisDao;
import com.ns.common.util.redis.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuezhucao on 16/7/14.
 */
@Service
public class WxAuthRedisDao extends AbsNSRedisDao {
    public String getOpenIdByAccessToken(String accessToken) throws Throwable {
        return get(RedisUtil.getWxAccessTokenKey(accessToken));
    }

    public String getRefreshToken(String accessToken) throws Throwable {
        return get(RedisUtil.getWxRefreshTokenKey(accessToken));
    }

    public void createAccessToken(String accessToken, String openId, int expire) throws Throwable {
        String key = RedisUtil.getWxAccessTokenKey(accessToken);
        set(key, openId);
        expire(key, expire, TimeUnit.SECONDS);
    }

    public void createRefreshToken(String accessToken, String refreshToken, int expire) throws Throwable {
        String key = RedisUtil.getWxRefreshTokenKey(accessToken);
        set(key, refreshToken);
        expire(key, expire, TimeUnit.SECONDS);
    }

    public void deleteRefreshToken(String accessToken) throws Throwable {
        delete(RedisUtil.getWxRefreshTokenKey(accessToken));
    }

}
