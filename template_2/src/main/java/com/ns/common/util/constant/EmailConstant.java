package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 16/4/29.
 */
public interface EmailConstant {
    String ENCODING = "UTF-8";

    interface Template {
        Long WAITING_RE_CONFIRM_ORDER = 1L;
        Long CONFIRM_ORDER = 2L;
        Long GRAB_ORDER = 3L;
        Long DELIVER_ORDER = 4L;

        Long NEW_SUGGESTION = 5L;

        Long NEW_SHIPPER_CAND = 6L;
        Long NEW_DRIVER_CAND = 7L;
    }
}
