package com.ns.common.util.bean;

import com.ns.common.util.constant.TimerTaskConstant;
import org.apache.commons.lang.ArrayUtils;

/**
 * Created by xuezhucao on 16/4/11.
 */
public class TimerTaskUtil {
    public static boolean isValidType(Integer type) {
        return ArrayUtils.contains(TimerTaskConstant.Type.ALL, type);
    }
}
