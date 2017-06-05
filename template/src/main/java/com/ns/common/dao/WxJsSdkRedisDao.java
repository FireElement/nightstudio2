package com.ns.common.dao;

import com.ns.common.dao.spi.redis.AbsNSRedisDao;
import com.ns.common.util.redis.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuezhucao on 16/2/4.
 */
@Service
public class WxJsSdkRedisDao extends AbsNSRedisDao {
    public String getAccessToken() throws Throwable {
        return get(RedisUtil.getWxJsSdkAccessTokenKey());
    }

    public void setAccessToken(String accessToken, int expire) throws Throwable {
        String key = RedisUtil.getWxJsSdkAccessTokenKey();
        set(key, accessToken);
        expire(key, expire, TimeUnit.SECONDS);
    }

    public String getTicket() throws Throwable {
        return get(RedisUtil.getWxJsSdkTicketKey());
    }

    public void setTicket(String ticket, int expire) throws Throwable {
        String key = RedisUtil.getWxJsSdkTicketKey();
        set(key, ticket);
        expire(key, expire, TimeUnit.SECONDS);
    }
}
