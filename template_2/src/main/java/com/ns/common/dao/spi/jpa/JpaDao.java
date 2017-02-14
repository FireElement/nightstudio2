package com.ns.common.dao.spi.jpa;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by caoxuezhu on 2017/2/13.
 */
public interface JpaDao<T, ID extends Serializable> extends CrudRepository<T, ID> {
}
