package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 15/8/19.
 */
public interface ParamConstant {
    interface Prefix {
    }

    interface Pattern {
        String LAST_APP_VERSION = "LAST_APP_VERSION_%s_%s";
        String LAST_APP_DOWNLOAD_URL = "LAST_APP_DOWNLOAD_URL_%s_%s";
    }

    interface Key {
        String REDIS_SERVER_1_IP = "REDIS_SERVER_1_IP";
        String REDIS_SERVER_1_PORT = "REDIS_SERVER_1_PORT";
        String REDIS_SERVER_1_PASSWD = "REDIS_SERVER_1_PASSWD";

        String OSS_ENDPOINT = "OSS_ENDPOINT";
        String OSS_BUCKET_IMG = "OSS_BUCKET_IMG";

        String ALI_ACCESS_KEY = "ALI_ACCESS_KEY";
        String ALI_SECRET = "ALI_SECRET";

        String JPUSH_CLIENT_1_MASTER_SECRET = "JPUSH_CLIENT_1_MASTER_SECRET";
        String JPUSH_CLIENT_1_APP_KEY = "JPUSH_CLIENT_1_APP_KEY";
        String JPUSH_MAX_RETRY_TIME = "JPUSH_MAX_RETRY_TIME";

        String SMS_WINNER_URL = "SMS_WINNER_URL";
        String SMS_WINNER_USER_CODE = "SMS_WINNER_USER_CODE";
        String SMS_WINNER_USER_PASS = "SMS_WINNER_USER_PASS";
        String SMS_WINNER_CHANNEL = "SMS_WINNER_CHANNEL";
        String SMS_WINNER_MULTI_URL = "SMS_WINNER_MULTI_URL";
        String SMS_WINNER_MULTI_USER_CODE = "SMS_WINNER_MULTI_USER_CODE";
        String SMS_WINNER_MULTI_USER_PASS = "SMS_WINNER_MULTI_USER_PASS";
        String SMS_WINNER_MULTI_CHANNEL = "SMS_WINNER_MULTI_CHANNEL";
        String SMS_WINNER_SIGN = "SMS_WINNER_SIGN";

        String RABBIT_HOST = "RABBIT_HOST";
        String RABBIT_USER_NAME = "RABBIT_USER_NAME";
        String RABBIT_PASSWORD = "RABBIT_PASSWORD";

        String MONGO_SERVER_1_ADDRESS = "MONGO_SERVER_1_ADDRESS";
        String MONGO_SERVER_1_PORT = "MONGO_SERVER_1_PORT";
        String MONGO_DB_NAME = "MONGO_DB_NAME";

        String LAST_CONFIG_VERSION = "LAST_CONFIG_VERSION";

        String EMAIL_HOST_NAME = "EMAIL_HOST_NAME";
        String SMTP_PORT = "SMTP_PORT";
        String EMAIL_FROM = "EMAIL_FROM";
        String EMAIL_USER_NAME = "EMAIL_USER_NAME";
        String EMAIL_PASSWORD = "EMAIL_PASSWORD";
        String EMAIL_USE_SSL = "EMAIL_USE_SSL";

        String SIG_SECRET = "SIG_SECRET";
        String SIG_IGNORE_SECRET = "SIG_IGNORE_SECRET";

        String IMG_SERVER = "IMG_SERVER";

        String BAIDU_MAP_AK = "BAIDU_MAP_AK";
    }
}
