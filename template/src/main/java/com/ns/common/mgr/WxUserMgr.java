package com.ns.common.mgr;

import com.ns.common.bean.wx.WxUser;
import com.ns.common.dao.WxUserDao;
import com.ns.common.dao.WxUserRedisDao;
import com.ns.common.util.datetime.DateTimeUtil;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/7/21.
 */
@Service
public class WxUserMgr {
    @Resource
    private WxUserDao dao;
    @Resource
    private WxUserRedisDao redisDao;

    public WxUser getByOpenId(String openId) throws Throwable {
        return dao.findByOpenId(openId);
    }

    public long getUserIdByOpenId(String openId) throws Throwable {
        if (StringUtils.isEmpty(openId)) {
            throw new ParameterException("open id为空");
        }
        Long result = redisDao.getUserIdByOpenId(openId);
        if (result == null) {
            WxUser wxUser = getByOpenId(openId);
            if (wxUser != null) {
                redisDao.createOpenId(wxUser.getOpenId(), wxUser.getUserId());
                redisDao.createUserId(wxUser.getUserId(), wxUser.getOpenId());
                result = wxUser.getUserId();
            } else {
                throw new NSException(ErrorCode.GET_USER_ID_BY_OPEN_ID_FAIL);
            }
        }
        return result;
    }

    public WxUser create(WxUser wxUser) throws Throwable {
        if (wxUser == null) {
            throw new ParameterException("微信用户对象为空");
        }
        if (StringUtils.isEmpty(wxUser.getOpenId())) {
            throw new ParameterException("open id为空");
        }
        wxUser.setCreateTime(DateTimeUtil.getNowDate());
        return dao.save(wxUser);
    }

    public void bindOpenIdAndUser(String openId, long userId) throws Throwable {
        WxUser wxUser = new WxUser();
        wxUser.setOpenId(openId);
        wxUser.setUserId(userId);
        dao.moidfyUserIdByOpenId(wxUser);
        redisDao.createOpenId(openId, userId);
        redisDao.createUserId(userId, openId);
    }

    public void unbindOpenIdAndUser(long userId) throws Throwable {
        String openId = redisDao.getOpenIdByUserId(userId);
        if (StringUtils.isNotEmpty(openId)) {
            WxUser wxUser = getByOpenId(openId);
            if (wxUser == null
                    || wxUser.getUserId() == null
                    || !wxUser.getUserId().equals(userId)) {
                throw new NSException(ErrorCode.UNBIND_WX_OPEN_ID_AND_USER_ID_FAIL);
            }
            wxUser = new WxUser();
            wxUser.setOpenId(openId);
            wxUser.setUserId(null);
            dao.moidfyUserIdByOpenId(wxUser);
            redisDao.deleteOpenId(openId);
        }
        redisDao.deleteUserId(userId);
    }
}
