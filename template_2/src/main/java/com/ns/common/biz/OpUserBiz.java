package com.ns.common.biz;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.OpUserDao;
import com.ns.common.dao.OpUserJdbcDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/6/12.
 */
@Service
public class OpUserBiz {
    @Resource
    private OpUserDao dao;
    @Resource
    private OpUserJdbcDao jdbcDao;

    public OpUser getByName(String name) {
        return dao.getByName(name);
    }

    public OpUser getByName1(String name) {
        return jdbcDao.getByName(name);
    }

    public int modifyPasswd(String name, String passwd) {
        return dao.modifyPasswd(name, passwd);
    }
}
