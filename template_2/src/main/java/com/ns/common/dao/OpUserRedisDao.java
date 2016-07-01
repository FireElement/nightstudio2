package com.ns.common.dao;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.spi.redis.AbsNSRedisDao;
import com.ns.common.util.redis.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * Created by xuezhucao on 16/7/1.
 */
@Service
public class OpUserRedisDao extends AbsNSRedisDao {
    public OpUser getOpUser(String name) throws Throwable {
        return getObj(RedisUtil.getOpUserKey(name), OpUser.class);
    }

    public void setOpUser(OpUser opUser) throws Throwable {
        setObj(RedisUtil.getOpUserKey(opUser.getName()), opUser);
    }
}
