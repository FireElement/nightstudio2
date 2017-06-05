package com.ns.common.util.constant;

/**
 * Created by caoxuezhu01 on 14-9-21.
 */
public interface FilterConstant {
    String[] TOKEN_FILTER_IGNORE_PATHS = {
            "app/app/getLastVersionAndDownloadUrl",
            "app/token/checkToken",
            "app/token/invalidToken",
            "app/config/getLastVersion",
            "app/config/getLastConfig",
            "wx/accessToken/checkAndRefreshAccessToken",
            "wx/accessToken/createAccessToken",
            "wx/accessToken/loginWithAccessToken",
            "wx/jssdk/getConfig"
    };
    String[] TOKEN_FILTER_PATHS = {
            PathConstant.APP_PREFIX,
            PathConstant.WX_PREFIX
    };

    String[] WEB_TOKEN_FILTER_IGNORE_PATHS = {
            "web/opUser/login.do",
            "web/opUser/logout.do",
            "web/token/invalidToken"
    };
    String[] WEB_TOKEN_FILTER_PATHS = {
            PathConstant.WEB_PREFIX
    };

    String[] SIG_FILTER_IGNORE_PATHS = {
    };
    String[] SIG_FILTER_PATHS = {
            PathConstant.WEB_PREFIX,
            PathConstant.APP_PREFIX
    };
}
