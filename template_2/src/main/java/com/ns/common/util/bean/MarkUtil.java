package com.ns.common.util.bean;

import com.ns.common.util.constant.MarkConstant;
import org.apache.commons.lang.ArrayUtils;

/**
 * Created by xuezhucao on 16/4/26.
 */
public class MarkUtil {
    public static boolean isValidType(Integer type) {
        return ArrayUtils.contains(MarkConstant.Type.ALL, type);
    }
}
