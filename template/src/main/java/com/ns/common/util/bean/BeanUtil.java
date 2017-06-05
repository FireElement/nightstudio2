package com.ns.common.util.bean;

import org.apache.commons.lang.ArrayUtils;

/**
 * Created by xuezhucao on 16/11/24.
 */
public abstract class BeanUtil {
    public static boolean containsInteger(Integer[] arr, Integer obj) {
        return ArrayUtils.contains(arr, obj);
    }

    public static boolean containsLong(Long[] arr, Long obj) {
        return ArrayUtils.contains(arr, obj);
    }
}
