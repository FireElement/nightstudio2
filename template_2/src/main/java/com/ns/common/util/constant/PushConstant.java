package com.ns.common.util.constant;

import java.util.HashMap;
import java.util.Map;

public class PushConstant {
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_PAGE = "page";

    public interface Template {
        Long WAITING_CONFIRM_ORDER = 1L;
    }

    public interface Page {
        String DRIVER_ORDER_DETAIL = "driver/orderDetail";  //司机端货源详情页
    }

    public static final Map<Long, String> PAGE_MAP = new HashMap<Long, String>(10);

    static {
        PAGE_MAP.put(Template.WAITING_CONFIRM_ORDER, Page.DRIVER_ORDER_DETAIL);
    }

}
