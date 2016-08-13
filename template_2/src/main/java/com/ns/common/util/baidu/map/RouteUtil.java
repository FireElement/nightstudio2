package com.ns.common.util.baidu.map;

import com.ns.common.bean.map.route.RouteItem;
import com.ns.common.bean.map.route.RouteResult;
import com.ns.common.util.exception.sys.ParameterException;
import com.ns.common.util.exception.sys.SystemInternalException;
import com.ns.common.util.gson.GsonUtil;
import com.ns.common.util.http.HttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by xuezhucao on 16/7/6.
 */
@Service
public class RouteUtil extends BaiduMapUtil {
    public RouteItem driving(long startLongitude, long startLatitude,
                             long targetLongitude, long targetLatitude) throws Throwable {
        String url = "/routematrix/v2/driving";

        if (startLongitude <= 0) {
            throw new ParameterException("无效的经度");
        }
        if (startLatitude <= 0) {
            throw new ParameterException("无效的纬度");
        }
        if (targetLongitude <= 0) {
            throw new ParameterException("无效的经度");
        }
        if (targetLatitude <= 0) {
            throw new ParameterException("无效的纬度");
        }
        String origins = String.valueOf(Double.valueOf(startLatitude) / 1000000) + "," +
                String.valueOf(Double.valueOf(startLongitude) / 1000000);
        origins = origins + "|" + origins;
        String destinations = String.valueOf(Double.valueOf(targetLatitude) / 1000000) + "," +
                String.valueOf(Double.valueOf(targetLongitude) / 1000000);
        destinations = destinations + "|" + destinations;
        String json = HttpUtil.getGetContent(URL + url,
                "origins", origins,
                "destinations", destinations,
                "ak", this.getAk());
        RouteResult result = GsonUtil.fromJson(json, RouteResult.class);
        if (result == null || CollectionUtils.isEmpty(result.getResult())) {
            throw new SystemInternalException();
        }
        return result.getResult().get(0);
    }

    public static void main(String[] args) {
        try {
            RouteItem result = new RouteUtil().driving(117491162, 39286967, 125429627, 43907569);
            System.out.println(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
