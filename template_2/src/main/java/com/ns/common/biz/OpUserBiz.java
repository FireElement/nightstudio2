package com.ns.common.biz;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.OpUserDao;
import com.ns.common.dao.OpUserJdbcDao;
import com.ns.common.mgr.OpUserMgr;
import com.ns.common.util.datetime.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuezhucao on 16/6/12.
 */
@Service
public class OpUserBiz {
    @Resource
    private OpUserMgr mgr;
    @Resource
    private OpUserDao dao;
    @Resource
    private OpUserJdbcDao jdbcDao;

    public OpUser getByName(String name) throws Throwable {
        return mgr.getByName(name);
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
