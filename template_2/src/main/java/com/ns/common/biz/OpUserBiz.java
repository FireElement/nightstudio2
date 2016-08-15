package com.ns.common.biz;

import com.ns.common.bean.OpUser;
import com.ns.common.bean.Token;
import com.ns.common.dao.OpUserDao;
import com.ns.common.mgr.OpUserMgr;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private WebTokenBiz webTokenBiz;

    public OpUser getById(long id) throws Throwable {
        return mgr.getById(id);
    }

    public OpUser getByName(String name) throws Throwable {
        return dao.getByName(name);
    }

    public String getPasswdByName(String name) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new ParameterException("用户名为空");
        }
        return dao.getPasswdByName(name);
    }

    public Token login(String name, String passwd) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new ParameterException("用户名为空");
        }
        if (StringUtils.isEmpty(passwd)) {
            throw new ParameterException("密码为空");
        }
        OpUser opUser = getByName(name);
        String p = getPasswdByName(name);
        if (!passwd.equals(p)) {
            throw new NSException(ErrorCode.WRONG_PASSWD);
        }

        Token result = new Token();
        result.setUserId(opUser.getId());
        return webTokenBiz.create(result);
    }

    public void logout(Token token) throws Throwable {
        webTokenBiz.delete(token);
    }

    public int modifyPasswd(String name, String passwd) {
        return dao.modifyPasswd(name, passwd);
    }
}
