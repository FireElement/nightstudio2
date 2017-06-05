package com.ns.common.dao;

import com.ns.common.bean.EmailTemplate;
import com.ns.common.dao.spi.jpa.JpaDao;

import java.util.List;

public interface EmailTemplateDao extends JpaDao<EmailTemplate, Long> {
    List<EmailTemplate> findAll();
}