package com.ns.common.dao.spi.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/8/4.
 */
public abstract class AbsNsMongoDao<T> {
    @Resource
    private Datastore ds;

    public Query<T> createQuery(Class<T> tClass) throws Throwable {
        if (tClass == null) {
            return null;
        }
        return ds.createQuery(tClass);
    }

    public String insert(T t) throws Throwable {
        if (t == null) {
            return null;
        }
        return (String) ds.save(t).getId();
    }
}
