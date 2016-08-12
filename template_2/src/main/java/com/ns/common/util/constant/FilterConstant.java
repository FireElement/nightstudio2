package com.ns.common.util.constant;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public interface FilterConstant {
    String[] TOKEN_FILTER_IGNORE_PATHS = {
            "app/app/getLastVersionAndDownloadUrl",
            "app/token/checkToken",
            "app/token/invalidToken"
    };
    String[] TOKEN_FILTER_PATHS = {
            PathConstant.APP_PREFIX
    };
    String[] SIG_FILTER_IGNORE_PATHS = {
    };

    String[] SIG_FILTER_PATHS = {
            PathConstant.WEB_PREFIX,
            PathConstant.APP_PREFIX
    };
}
