package com.ns.common.biz;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.OpUserDao;
import com.ns.common.dao.OpUserJdbcDao;
import com.ns.common.util.datetime.DateTimeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuezhucao on 16/6/12.
 */
@Service
public class OpUserBiz {
    @Resource
    private OpUserDao dao;
    @Resource
    private OpUserJdbcDao jdbcDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public OpUser getByName(String name) {
        OpUser result = dao.getByName(name);
        redisTemplate.opsForValue().setIfAbsent("user_" + name, result.toString());
        redisTemplate.expire("user_" + name, 1, TimeUnit.DAYS);
        redisTemplate.opsForHash().put("user", name, result.toString());
        redisTemplate.expire("user", 1, TimeUnit.DAYS);
        return result;
    }

    public OpUser getByName1(String name) {
        return jdbcDao.getByName(name);
    }

    public List<OpUser> getAll() {
        return jdbcDao.getAll();
    }

    public OpUser insert(OpUser opUser) throws Throwable {
        opUser.setCreateTime(DateTimeUtil.getNowDate());
        jdbcDao.insert(opUser);
        return opUser;
    }

    public OpUser modify(OpUser opUser) throws Throwable {
        return jdbcDao.update(opUser);
    }

    public int modifyPasswd(String name, String passwd) {
        return dao.modifyPasswd(name, passwd);
    }
}
