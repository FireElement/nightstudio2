package com.ns.common.mgr;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.OpUserDao;
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
    private OpUserRedisDao redisDao;

    public OpUser getById(long id) throws Throwable {
        OpUser result = redisDao.getOpUser(id);
        if (result == null) {
            result = dao.getById(id);
            redisDao.setOpUser(result);
        }
        return result;
    }
}
