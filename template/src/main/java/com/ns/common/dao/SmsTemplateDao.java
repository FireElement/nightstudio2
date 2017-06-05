package com.ns.common.dao;

import com.ns.common.bean.SmsTemplate;
import com.ns.common.dao.spi.jpa.JpaDao;

import java.util.List;

public interface SmsTemplateDao extends JpaDao<SmsTemplate, Long> {
    List<SmsTemplate> findAll();
}