package com.ns.common.biz;

import com.ns.common.bean.App;
import com.ns.common.util.bean.AppUtil;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 16/4/26.
 */
@Service
public class AppBiz {
    @Resource
    private ParamBiz paramBiz;

    public Map<String, String> getLastVersionAndDownloadUrl(App app) throws Throwable {
        if (!AppUtil.isValidPlatform(app.getPlatform())) {
            throw new ParameterException("无效的平台");
        }
        if (!AppUtil.isValidAppId(app.getAppId())) {
            throw new ParameterException("无效的appId");
        }

        Map<String, String> result = new HashMap<>(2);

        String version = paramBiz.getLastAppVersion(app.getPlatform(), app.getAppId());
        if (StringUtils.isEmpty(version)) {
            throw new NSException(ErrorCode.GET_LAST_APP_VERSION_FAIL);
        }
        result.put("version", version);

        String downloadUrl = paramBiz.getLastAppDownloadUrl(app.getPlatform(), app.getAppId());
        if (StringUtils.isEmpty(downloadUrl)) {
            throw new NSException(ErrorCode.GET_LAST_APP_DOWNLOAD_URL_FAIL);
        }
        result.put("downloadUrl", downloadUrl);

        return result;
    }
}
