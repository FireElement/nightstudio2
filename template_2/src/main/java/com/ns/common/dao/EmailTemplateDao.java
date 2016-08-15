package com.ns.common.dao;

import com.ns.common.bean.EmailTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailTemplateDao extends CrudRepository<EmailTemplate, Long> {
    List<EmailTemplate> findAll();
}