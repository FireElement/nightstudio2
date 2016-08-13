package com.ns.common.dao;

import com.ns.common.bean.mongo.MongoTest;
import com.ns.common.dao.spi.mongodb.AbsNsMongoDao;
import org.springframework.stereotype.Service;

/**
 * Created by xuezhucao on 16/8/13.
 */
@Service
public class MongoTestMongoDao extends AbsNsMongoDao<MongoTest> {
}
