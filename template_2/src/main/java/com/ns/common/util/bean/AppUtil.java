package com.ns.common.util.bean;

import com.ns.common.util.constant.AppConstant;
import org.apache.commons.lang.ArrayUtils;

/**
 * Created by xuezhucao on 16/4/26.
 */
public class AppUtil {
    public static boolean isValidPlatform(Integer platform) {
        return ArrayUtils.contains(AppConstant.Platform.ALL, platform);
    }

    public static boolean isValidAppId(Integer appId) {
        return ArrayUtils.contains(AppConstant.AppId.ALL, appId);
    }
}
