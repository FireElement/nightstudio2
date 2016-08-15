package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 15/7/19.
 */
public interface RedisConstant {
    interface Key {
        String SPACE = "_";
        String TOKEN = "token_%s";
        String WEB_TOKEN = "web_token_%s";
        String OP_USER = "op_user_%s";
        String WX_ACCESS_TOKEN = "wx_access_token_%s";
        String WX_REFRESH_TOKEN = "wx_refresh_token_%s";
        String WX_OPEN_ID = "wx_open_id_%s";
        String WX_USER_ID = "wx_user_id_%s";
        String WX_JS_SDK_ACCESS_TOKEN = "wx_js_sdk_access_token";
        String WX_JS_SDK_TICKET = "wx_js_sdk_ticket";
    }
}
