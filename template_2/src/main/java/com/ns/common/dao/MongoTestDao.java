package com.ns.common.dao;

import com.ns.common.bean.MongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by xuezhucao on 16/8/13.
 */
public interface MongoTestDao extends MongoRepository<MongoTest, String> {

    public List<MongoTest> findByFirstName(String firstName);

}
