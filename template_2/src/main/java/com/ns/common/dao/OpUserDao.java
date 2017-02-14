package com.ns.common.dao;

import com.ns.common.bean.OpUser;
import com.ns.common.dao.spi.jpa.JpaDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuezhucao on 16/6/12.
 */
public interface OpUserDao extends JpaDao<OpUser, Long> {
    OpUser getById(long id);
    OpUser getByName(String name);

    @Query("select passwd from OpUser where name = :name")
    String getPasswdByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update OpUser set passwd = :passwd where name = :name")
    int modifyPasswd(@Param("name") String name, @Param("passwd") String passwd);
}
