package com.ns.common.dao;

import com.ns.common.bean.PushTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PushTemplateDao extends CrudRepository<PushTemplate, Long> {
    List<PushTemplate> findAll();
}