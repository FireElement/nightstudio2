package com.ns.common.dao.spi.redis;

import com.ns.common.util.gson.GsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuezhucao on 16/7/1.
 */
public class AbsNSRedisDao {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    protected String get(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    protected Long getLong(String key) throws Throwable {
        String value = get(key);
        if (value != null) {
            return Long.valueOf(value);
        }
        return null;
    }

    protected <T> T getObj(String key, Class<T> classOfT) throws Throwable {
        String value = get(key);
        if (value != null) {
            return GsonUtil.fromJson(value, classOfT);
        }
        return null;
    }

    protected void set(String key, String value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(value)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }

    protected <T> void setObj(String key, T obj) throws Throwable {
        if (StringUtils.isEmpty(key)
                || obj == null) {
            return;
        }
        String value = GsonUtil.toJson(obj);
        set(key, value);
        return;
    }

    protected void hset(String key, String field, String value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)) {
            return;
        }
        redisTemplate.opsForHash().put(key, field, value);
    }

    protected Set<String> hkeys(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return new HashSet<String>(0);
        }
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        return ops.keys(key);
    }

    protected Map<String, String> hgetAll(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return new HashMap<String, String>(0);
        }
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        return ops.entries(key);
    }

    protected boolean setnx(String key, String value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(value)) {
            return false;
        }
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        return false;
    }

    protected Long incr(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().increment(key, 1);
    }

    protected Long decr(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().increment(key, -1);
    }

    protected void delete(String key) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }

    protected void hDelete(String key, String field) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)) {
            return;
        }
        redisTemplate.opsForHash().delete(key, field);
    }

    protected Long hincr(String key, String field, long value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)
                || value < 0) {
            return null;
        }
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    protected Long hdecr(String key, String field, long value) throws Throwable {
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(field)
                || value < 0) {
            return null;
        }
        return redisTemplate.opsForHash().increment(key, field, -value);
    }

    protected void expire(String key, int expire, TimeUnit timeUnit) throws Throwable {
        if (StringUtils.isEmpty(key)
                || expire < 0) {
            return;
        }
        redisTemplate.expire(key, expire, timeUnit);
    }

    protected void defer(String key, int expire, TimeUnit timeUnit) throws Throwable {
        long ttl = redisTemplate.getExpire(key, timeUnit);
        if (ttl > -2 && expire > ttl) {
            expire(key, expire, timeUnit);
        }
    }

    protected Long zremRange(String key, double start, double end) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForZSet().removeRangeByScore(key, start, end);
    }

    protected Set<String> zrange(String key, long start, long end) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    protected Boolean zadd(String key, String member, long score) throws Throwable {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForZSet().add(key, member, Double.valueOf(score));
    }
}
