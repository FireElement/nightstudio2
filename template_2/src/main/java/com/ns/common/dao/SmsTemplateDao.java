package com.ns.common.dao;

import com.ns.common.bean.SmsTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmsTemplateDao extends CrudRepository<SmsTemplate, Long> {
    List<SmsTemplate> findAll();
}