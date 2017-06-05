package com.ns.common.util.baidu.map;

import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuezhucao on 16/7/6.
 */
@Service
public class BaiduMapUtil {
    @Resource
    private ParamBiz paramBiz;

    protected static final String URL = "http://api.map.baidu.com";

    public String getAk() throws Throwable {
        return paramBiz.getStringByName(ParamConstant.Key.BAIDU_MAP_AK);
    }
}
