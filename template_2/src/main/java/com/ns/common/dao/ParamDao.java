package com.ns.common.dao;

import com.ns.common.bean.Param;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParamDao extends CrudRepository<Param, String> {
    List<Param> findAll();
}
