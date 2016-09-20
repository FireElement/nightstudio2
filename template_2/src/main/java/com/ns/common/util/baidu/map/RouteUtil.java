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

    public List<RouteItem> driving(long[] startPoses, long[] targetPoses) throws Throwable {
        String url = "/routematrix/v2/driving";

        if (ArrayUtils.isEmpty(startPoses)) {
            throw new ParameterException("起点数组为空");
        }
        if (ArrayUtils.isEmpty(targetPoses)) {
            throw new ParameterException("终点数组为空");
        }
        if (startPoses.length % 2 != 0) {
            throw new ParameterException("起点数组长度有误");
        }
        if (targetPoses.length != startPoses.length) {
            throw new ParameterException("终点数组长度有误");
        }

        long startLongitude;
        long startLatitude;
        StringBuilder builder = new StringBuilder(startPoses.length * 22);
        for (int i = 0; i < startPoses.length; i += 2) {
            startLongitude = startPoses[i];
            startLatitude = startPoses[i + 1];
            if (startLongitude <= 0) {
                throw new ParameterException("无效的经度");
            }
            if (startLatitude <= 0) {
                throw new ParameterException("无效的纬度");
            }
            builder.append(String.valueOf(Double.valueOf(startLatitude) / 1000000))
                    .append(",")
                    .append(String.valueOf(Double.valueOf(startLongitude) / 1000000))
                    .append("|");
        }
        String origins = builder.substring(0, builder.length() - 1);

        long targetLongitude;
        long targetLatitude;
        builder = new StringBuilder(targetPoses.length * 22);
        for (int i = 0; i < targetPoses.length; i += 2) {
            targetLongitude = targetPoses[i];
            targetLatitude = targetPoses[i + 1];
            if (targetLongitude <= 0) {
                throw new ParameterException("无效的经度");
            }
            if (targetLatitude <= 0) {
                throw new ParameterException("无效的纬度");
            }
            builder.append(String.valueOf(Double.valueOf(targetLatitude) / 1000000))
                    .append(",")
                    .append(String.valueOf(Double.valueOf(targetLongitude) / 1000000))
                    .append("|");
        }
        String destinations = builder.substring(0, builder.length() - 1);

        String json = HttpUtil.getGetContent(BaiduMapUtil.URL + url,
                "origins", origins,
                "destinations", destinations,
                "ak", this.getAk());
        RouteResult routeResult = GsonUtil.fromJson(json, RouteResult.class);
        if (routeResult == null || CollectionUtils.isEmpty(routeResult.getResult())) {
            throw new SystemInternalException();
        }

        List<RouteItem> routeItems = routeResult.getResult();
        List<RouteItem> result = new ArrayList<>(routeItems.size() / targetPoses.length / 2);

        for (int i = 0, j = 0; i < routeItems.size(); i += targetPoses.length / 2, j++) {
            result.add(routeItems.get(i + j));
        }

        return result;
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
