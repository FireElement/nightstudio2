package com.ns.common.biz;

import com.ns.common.bean.Token;
import com.ns.common.bean.wx.auth.AccessTokenResult;
import com.ns.common.bean.wx.auth.RefreshTokenResult;
import com.ns.common.dao.WxAuthRedisDao;
import com.ns.common.util.constant.WxConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import com.ns.common.util.gson.GsonUtil;
import com.ns.common.util.http.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/7/14.
 */
@Service
public class WxAuthBiz {
    private static Log logger = LogFactory.getLog(WxAuthBiz.class);
    @Resource
    private WxAuthRedisDao redisDao;
    @Resource
    private WxUserBiz wxUserBiz;
    @Resource
    private TokenBiz tokenBiz;

    public String getOpenIdByAccessToken(String accessToken) throws Throwable {
        if (StringUtils.isEmpty(accessToken)) {
            throw new ParameterException("access token为空");
        }
        String openId = redisDao.getOpenIdByAccessToken(accessToken);
        if (StringUtils.isEmpty(openId)) {
            throw new NSException(ErrorCode.INVALID_WX_ACCESS_TOKEN);
        }
        return openId;
    }

    public String refreshAccessToken(String accessToken) throws Throwable {
        if (StringUtils.isEmpty(accessToken)) {
            throw new ParameterException("access token为空");
        }

        String refreshToken = redisDao.getRefreshToken(accessToken);
        if (StringUtils.isEmpty(refreshToken)) {
            throw new NSException(ErrorCode.REFRESH_WX_ACCESS_TOKEN_FAIL);
        }

        String url = String.format(WxConstant.Auth.URL_REFRESH_TOKEN, WxConstant.APP_ID, refreshToken);
        String content = HttpUtil.getContent(url);
        RefreshTokenResult refreshTokenResult = GsonUtil.fromJson(content, RefreshTokenResult.class);

        String result = refreshTokenResult.getAccess_token();
        String openId = refreshTokenResult.getOpenid();
        String newRefreshToken = refreshTokenResult.getRefresh_token();
        Integer expire = refreshTokenResult.getExpires_in();

        if (StringUtils.isEmpty(result)
                || StringUtils.isEmpty(openId)
                || StringUtils.isEmpty(newRefreshToken)
                || expire == null) {
            throw new NSException(ErrorCode.REFRESH_WX_ACCESS_TOKEN_FAIL);
        }

        redisDao.createAccessToken(result, openId, expire);
        redisDao.deleteRefreshToken(accessToken);
        redisDao.createRefreshToken(result, newRefreshToken, WxConstant.Auth.REFRESH_TOKEN_EXPIRE);

        return result;
    }

    public String checkAndRefreshAccessToken(String accessToken) throws Throwable {
        if (StringUtils.isEmpty(accessToken)) {
            throw new ParameterException("access token为空");
        }
        try {
            getOpenIdByAccessToken(accessToken);
            return accessToken;
        } catch (Throwable e) {
            return refreshAccessToken(accessToken);
        }
    }

    public String createAccessToken(String code) throws Throwable {
        if (StringUtils.isEmpty(code)) {
            throw new ParameterException("code为空");
        }

        String url = String.format(WxConstant.Auth.URL_ACCESS_TOKEN, WxConstant.APP_ID, WxConstant.SECRET, code);
        String content = HttpUtil.getContent(url);
        AccessTokenResult accessTokenResult = GsonUtil.fromJson(content, AccessTokenResult.class);

        String result = accessTokenResult.getAccess_token();
        String openId = accessTokenResult.getOpenid();
        String refreshToken = accessTokenResult.getRefresh_token();
        Integer expire = accessTokenResult.getExpires_in();

        if (StringUtils.isEmpty(result)
                || StringUtils.isEmpty(openId)
                || StringUtils.isEmpty(refreshToken)
                || expire == null) {
            throw new NSException(ErrorCode.CREATE_WX_ACCESS_TOKEN_FAIL);
        }

        redisDao.createAccessToken(result, openId, expire);
        redisDao.createRefreshToken(result, refreshToken, WxConstant.Auth.REFRESH_TOKEN_EXPIRE);

        return result;
    }

    public Token loginWithAccessToken(String accessToken) throws Throwable {
        if (StringUtils.isEmpty(accessToken)) {
            throw new ParameterException("access token为空");
        }
        String openId = getOpenIdByAccessToken(accessToken);
        long userId = wxUserBiz.getUserIdByOpenId(openId);
        return tokenBiz.create(userId);
    }

}
