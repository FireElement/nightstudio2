package com.ns.common.dao;

import com.ns.common.bean.mongo.Mark;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by xuezhucao on 16/8/13.
 */
public interface MarkDao extends MongoRepository<Mark, String> {

    Mark findByValue(String value);

}
