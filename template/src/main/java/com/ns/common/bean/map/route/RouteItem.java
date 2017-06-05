package com.ns.common.bean.map.route;

/**
 * Created by xuezhucao on 16/7/6.
 */
public class RouteItem {
    private RouteDistance distance;
    private RouteDuration duration;

    public RouteDistance getDistance() {
        return distance;
    }

    public void setDistance(RouteDistance distance) {
        this.distance = distance;
    }

    public RouteDuration getDuration() {
        return duration;
    }

    public void setDuration(RouteDuration duration) {
        this.duration = duration;
    }
}
