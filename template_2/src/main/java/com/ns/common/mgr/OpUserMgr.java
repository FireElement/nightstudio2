package com.ns.common.mgr;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.OpUserDao;
import com.ns.common.dao.OpUserJdbcDao;
import com.ns.common.dao.OpUserRedisDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/7/1.
 */
@Service
public class OpUserMgr {
    @Resource
    private OpUserDao dao;
    @Resource
    private OpUserJdbcDao jdbcDao;
    @Resource
    private OpUserRedisDao redisDao;

    public OpUser getByName(String name) throws Throwable {
        OpUser result = redisDao.getOpUser(name);
        if (result == null) {
            result = dao.getByName(name);
            redisDao.setOpUser(result);
        }
        return result;
    }
}
