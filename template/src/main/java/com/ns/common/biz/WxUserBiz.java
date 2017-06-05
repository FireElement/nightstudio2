package com.ns.common.biz;

import com.ns.common.bean.wx.WxUser;
import com.ns.common.mgr.WxUserMgr;
import com.ns.common.util.constant.WxConstant;
import com.ns.common.util.http.HttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/7/21.
 */
@Service
public class WxUserBiz {
    @Resource
    private WxUserMgr userMgr;
    @Resource
    private WxAuthBiz authBiz;

    public void bindOpenIdAndUser(String accessToken, long userId) throws Throwable {
        String openId = authBiz.getOpenIdByAccessToken(accessToken);

        WxUser wxUser = userMgr.getByOpenId(openId);
        if (wxUser == null) {
            String url = String.format(WxConstant.Auth.URL_USER_INFO, accessToken, openId);
            String userInfo = HttpUtil.getContent(url);
            wxUser = new WxUser();
            wxUser.setOpenId(openId);
            wxUser.setUserInfo(userInfo);
            userMgr.create(wxUser);
        } else if (wxUser.getUserId() != null) {
            userMgr.unbindOpenIdAndUser(wxUser.getUserId());
        }

        userMgr.unbindOpenIdAndUser(userId);
        userMgr.bindOpenIdAndUser(openId, userId);
    }

    public long getUserIdByOpenId(String openId) throws Throwable {
        return userMgr.getUserIdByOpenId(openId);
    }
}
