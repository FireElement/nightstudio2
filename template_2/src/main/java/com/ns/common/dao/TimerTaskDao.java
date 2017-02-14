package com.ns.common.dao;

import com.ns.common.bean.TimerTask;
import com.ns.common.dao.spi.jpa.JpaDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by xuezhucao on 16/8/15.
 */
public interface TimerTaskDao extends JpaDao<TimerTask, Long> {

    List<TimerTask> findByProcessor(String processor);

    @Transactional
    @Modifying
    @Query("update TimerTask set processor = :processor where processor is null and processTime <= :processTime")
    int modifyProcessor(@Param("processor") String processor, @Param("processTime") Date processTime);

    @Transactional
    @Modifying
    @Query("delete from TimerTask where processor = :processor")
    int deleteByProcessor(@Param("processor") String processor);
}
