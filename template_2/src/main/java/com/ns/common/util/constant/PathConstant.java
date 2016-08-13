package com.ns.common.util.constant;

/**
 * Created by caoxuezhu01 on 14-10-24.
 */
public interface PathConstant {
    String APP_PREFIX = "/app";
    String WEB_PREFIX = "/web";
    String COMMON_PREFIX = "/common";

    String INVALID_TOKEN = APP_PREFIX + "/token/invalidToken";
    String WEB_INVALID_TOKEN = WEB_PREFIX + "/token/invalidToken";
    String INVALID_SIG = COMMON_PREFIX + "/sig/invalidSig";
}
