package com.ns.common.util.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by xuezhucao on 16/8/13.
 */
//@Configuration
public class MongodbConfig {
    @Bean
    MongoClient mongoClient(ParamBiz paramBiz) throws Throwable {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(30).build();
        MongoClient result = new MongoClient(
                new ServerAddress(
                        paramBiz.getStringByName(ParamConstant.Key.MONGO_SERVER_1_ADDRESS),
                        paramBiz.getIntByName(ParamConstant.Key.MONGO_SERVER_1_PORT)),
                options);
        return result;
    }

    @Bean
    MongoDbFactory mongoDbFactory(MongoClient mongoClient, ParamBiz paramBiz) throws Throwable {
        SimpleMongoDbFactory result = new SimpleMongoDbFactory(
                mongoClient,
                paramBiz.getStringByName(ParamConstant.Key.MONGO_DB_NAME));
        return result;
    }

    @Bean
    DB mongoDb(MongoClient mongoClient, ParamBiz paramBiz) throws Throwable {
        return mongoClient.getDB(paramBiz.getStringByName(ParamConstant.Key.MONGO_DB_NAME));
    }

    @Bean
    Datastore datastore(MongoClient mongoClient, ParamBiz paramBiz) throws Throwable {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.ns");
        Datastore result = morphia.createDatastore(
                mongoClient,
                paramBiz.getStringByName(ParamConstant.Key.MONGO_DB_NAME));
        result.ensureIndexes();
        return result;
    }
}
