package com.ns.common.dao.spi.jpa;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caoxuezhu on 2017/2/13.
 */
public interface JpaDao<T, ID extends Serializable> extends CrudRepository<T, ID> {
    default List<T> saves(List<T> objs) {
        return IteratorUtils.toList(save(objs).iterator());
    }
}
