package com.ns.common.dao;

import com.ns.common.bean.Token;
import com.ns.common.dao.spi.redis.AbsNSRedisDao;
import com.ns.common.util.redis.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
@Service
public class TokenDao extends AbsNSRedisDao {
    private static Log logger = LogFactory.getLog(TokenDao.class);

    public Long getByToken(String token) throws Throwable {
        return this.getLong(RedisUtil.getTokenKey(token));
    }

    public Boolean create(Token token) throws Throwable {
        String key = RedisUtil.getTokenKey(token.getToken());
        if (this.setnx(key, String.valueOf(token.getUserId()))) {
            this.expire(key, token.getExpire(), TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public void updateExpire(Token token) throws Throwable {
        expire(RedisUtil.getTokenKey(token.getToken()), token.getExpire(), TimeUnit.SECONDS);
    }

    public void delete(Token token) throws Throwable {
        delete(RedisUtil.getTokenKey(token.getToken()));
    }
}
