package com.ns.common.dao;

import com.ns.common.dao.spi.redis.AbsNSRedisDao;
import com.ns.common.util.redis.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * Created by xuezhucao on 16/7/21.
 */
@Service
public class WxUserRedisDao extends AbsNSRedisDao {

    public Long getUserIdByOpenId(String openId) throws Throwable {
        return getLong(RedisUtil.getWxOpenIdKey(openId));
    }

    public String getOpenIdByUserId(long userId) throws Throwable {
        return get(RedisUtil.getWxUserIdKey(userId));
    }

    public void createOpenId(String openId, long userId) throws Throwable {
        set(RedisUtil.getWxOpenIdKey(openId), String.valueOf(userId));
    }

    public void createUserId(long userId, String openId) throws Throwable {
        set(RedisUtil.getWxUserIdKey(userId), openId);
    }

    public void deleteOpenId(String openId) throws Throwable {
        delete(RedisUtil.getWxOpenIdKey(openId));
    }

    public void deleteUserId(long userId) throws Throwable {
        delete(RedisUtil.getWxUserIdKey(userId));
    }
}
