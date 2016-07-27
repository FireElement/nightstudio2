package com.ns.common.util.baidu.map;

import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xuezhucao on 16/7/6.
 */
@Component
public class BaiduMapUtil {
    @Autowired
    private ParamBiz paramBiz;

    protected static final String URL = "http://api.map.baidu.com";

    public String getAk() throws Throwable {
        return paramBiz.getStringByName(ParamConstant.Key.BAIDU_MAP_AK);
    }
}
