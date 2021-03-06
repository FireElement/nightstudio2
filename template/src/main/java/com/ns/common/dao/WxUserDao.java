package com.ns.common.dao;

import com.ns.common.bean.wx.WxUser;
import com.ns.common.dao.spi.jpa.JpaDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuezhucao on 16/7/21.
 */
public interface WxUserDao extends JpaDao<WxUser, Long> {

    WxUser findByOpenId(String openId);

    @Transactional
    @Modifying
    @Query("update WxUser set userId = :#{#wxUser.userId} where openId = :#{#wxUser.openId}")
    int moidfyUserIdByOpenId(@Param("wxUser") WxUser wxUser);
}
