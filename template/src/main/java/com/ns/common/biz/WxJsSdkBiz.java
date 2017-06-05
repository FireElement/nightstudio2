package com.ns.common.biz;

import com.ns.common.bean.wx.jssdk.WxAccessTokenResult;
import com.ns.common.bean.wx.jssdk.WxTicketResult;
import com.ns.common.dao.WxJsSdkRedisDao;
import com.ns.common.util.constant.WxConstant;
import com.ns.common.util.encrypt.EncryptUtil;
import com.ns.common.util.gson.GsonUtil;
import com.ns.common.util.http.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 16/2/4.
 */
@Service
public class WxJsSdkBiz {
    @Resource
    private WxJsSdkRedisDao redisDao;
    private Object accessTokenLock = new Object();
    private Object ticketLock = new Object();

    public String getAccessToken() throws Throwable {
        String result = redisDao.getAccessToken();
        if (StringUtils.isEmpty(result)) {
            synchronized (accessTokenLock) {
                result = redisDao.getAccessToken();
                if (StringUtils.isEmpty(result)) {
                    String httpResult = HttpUtil.getGetContent(
                            WxConstant.JsSdk.Url.GET_ACCESS_TOEN,
                            "grant_type", "client_credential",
                            "appid", WxConstant.APP_ID,
                            "secret", WxConstant.SECRET);
                    WxAccessTokenResult wxAccessTokenResult = GsonUtil.fromJson(httpResult, WxAccessTokenResult.class);
                    if (wxAccessTokenResult != null) {
                        result = wxAccessTokenResult.getAccess_token();
                        redisDao.setAccessToken(result, wxAccessTokenResult.getExpires_in() - 10);
                    }
                }
            }
        }
        return result;
    }

    public String getTicket() throws Throwable {
        String result = redisDao.getTicket();
        if (StringUtils.isEmpty(result)) {
            synchronized (ticketLock) {
                result = redisDao.getTicket();
                if (StringUtils.isEmpty(result)) {
                    String httpResult = HttpUtil.getGetContent(
                            WxConstant.JsSdk.Url.GET_TICKET,
                            "access_token", getAccessToken(),
                            "type", "jsapi");
                    WxTicketResult wxTicketResult = GsonUtil.fromJson(httpResult, WxTicketResult.class);
                    if (wxTicketResult != null) {
                        result = wxTicketResult.getTicket();
                        redisDao.setTicket(result, wxTicketResult.getExpires_in() - 10);
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Object> getWxConfig(String url) throws Throwable {
        long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = WxConstant.JsSdk.Config.NONCE_STR;
        String ticket = getTicket();
        String str = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s",
                ticket, nonceStr, timestamp, url);
        String sig = EncryptUtil.SHA1(str);
        Map<String, Object> result = new HashMap<>(4);
        result.put("appId", WxConstant.APP_ID);
        result.put("timestamp", timestamp);
        result.put("nonceStr", nonceStr);
        result.put("sig", sig);
        return result;
    }

}
