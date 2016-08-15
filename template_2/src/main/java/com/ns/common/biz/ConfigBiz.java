package com.ns.common.biz;

import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 16/4/25.
 */
@Component
public class ConfigBiz {
    @Autowired
    private ParamBiz paramBiz;

    public String getLastVersion() throws Throwable {
        String result = paramBiz.getStringByName(ParamConstant.Key.LAST_CONFIG_VERSION);
        if (StringUtils.isEmpty(result)) {
            throw new NSException(ErrorCode.GET_LAST_CONFIG_VERSION_FAIL);
        }
        return result;
    }

    public Map<String, Object> getLastConfig(String clientVersion) throws Throwable {
        if (getLastVersion().equals(clientVersion)) {
            throw new NSException(ErrorCode.ALREADY_LAST_CONFIG);
        }

        Map<String, Object> result = new HashMap<>(50);

        result.put("lastVersion", getLastVersion());

        result.put("imgServer",
                paramBiz.getStringByName(ParamConstant.Key.IMG_SERVER));

        return result;
    }
}
