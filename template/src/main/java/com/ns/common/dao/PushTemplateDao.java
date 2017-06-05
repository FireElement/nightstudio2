package com.ns.common.dao;

import com.ns.common.bean.PushTemplate;
import com.ns.common.dao.spi.jpa.JpaDao;

import java.util.List;

public interface PushTemplateDao extends JpaDao<PushTemplate, Long> {
    List<PushTemplate> findAll();
}