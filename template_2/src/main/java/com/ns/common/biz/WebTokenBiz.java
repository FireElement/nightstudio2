package com.ns.common.biz;

import com.ns.common.bean.Token;
import com.ns.common.dao.WebTokenDao;
import com.ns.common.util.constant.WebTokenConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import com.ns.common.util.string.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by caoxuezhu01 on 14-9-14.
 */
@Service
public class WebTokenBiz {
    Random random = new Random();
    @Resource
    protected WebTokenDao dao;

    public Token get(String token) throws Throwable {
        Long userId = dao.getByToken(token);
        if (userId == null) {
            throw new NSException(ErrorCode.INVALID_TOKEN);
        }
        Token result = new Token();
        result.setToken(token);
        result.setUserId(userId);
        return result;
    }

    public Token check(String token) throws Throwable {
        Token result = get(token);
        result.setToken(token);
        result.setExpire(WebTokenConstant.ALIVE_TIME);
        dao.updateExpire(result);
        return result;
    }

    public Token create(Token token) throws Throwable {
        if (token == null ||
            token.getUserId() == null) {
            throw new ParameterException();
        }
        long time = System.currentTimeMillis();
        String t = StringUtil.getStrOfLength(
                '0',
                String.valueOf(time % WebTokenConstant.TIME_PART_MOD),
                WebTokenConstant.TIME_PART_LENGTH);
        String r = StringUtil.getStrOfLength(
                '0',
                String.valueOf(random.nextInt() % WebTokenConstant.RANDOM_PART_MOD),
                WebTokenConstant.RANDOM_PART_LENGTH);
        t = t + r;
        token.setToken(t);
        token.setExpire(WebTokenConstant.ALIVE_TIME);
        if (!dao.create(token)) {
            throw new NSException(ErrorCode.CREATE_TOKEN_FAIL);
        }
        return token;
    }

    public void delete(Token token) throws Throwable {
        if (token == null ||
            token.getUserId() == null ||
            StringUtils.isEmpty(token.getToken())) {
            throw new ParameterException();
        }
        Token t = get(token.getToken());
        if (!t.getUserId().equals(token.getUserId())) {
            throw new NSException(ErrorCode.INVALID_TOKEN);
        }
        dao.delete(t);
    }
}
