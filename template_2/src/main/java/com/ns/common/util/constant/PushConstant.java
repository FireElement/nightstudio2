package com.ns.common.util.constant;

import java.util.HashMap;
import java.util.Map;

public class PushConstant {
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_PAGE = "page";

    public interface Template {
        Long TEST = 1L;
    }

    public interface Page {
        String PAGE1 = "page1";
    }

    public static final Map<Long, String> PAGE_MAP = new HashMap<Long, String>(10);

    static {
        PAGE_MAP.put(Template.TEST, Page.PAGE1);
    }

}
