package com.ns.common.biz;

import com.ns.common.bean.Param;
import com.ns.common.mgr.ParamMgr;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 15/8/19.
 */
@Service
public class ParamBiz {
    @Resource
    private ParamMgr mgr;

    public Param getByName(String name) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new ParameterException("名称为空");
        }
        Param result = mgr.getByName(name);
        if (result == null) {
            throw new NSException(ErrorCode.GET_PARAM_FAIL);
        }
        return result;
    }

    public String getStringByName(String name) throws Throwable {
        Param param = getByName(name);
        if (param == null) {
            return null;
        }
        return param.getValue();
    }

    public Integer getIntByName(String name) throws Throwable {
        String str = getStringByName(name);
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Integer.valueOf(str);
    }

    public Long getLongByName(String name) throws Throwable {
        String str = getStringByName(name);
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Long.valueOf(str);
    }

    public Boolean getBoolByName(String name) throws Throwable {
        String str = getStringByName(name);
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Boolean.valueOf(str);
    }

    public String getLastAppVersion(int platform, int appId) throws Throwable {
        return getStringByName(String.format(
                ParamConstant.Pattern.LAST_APP_VERSION, platform, appId));
    }

    public String getLastAppDownloadUrl(int platform, int appId) throws Throwable {
        return getStringByName(String.format(
                ParamConstant.Pattern.LAST_APP_DOWNLOAD_URL, platform, appId));
    }

}