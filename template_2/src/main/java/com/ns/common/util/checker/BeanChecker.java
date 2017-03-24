package com.ns.common.util.checker;

import com.ns.common.util.bean.BeanUtil;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by xuezhucao on 16/11/24.
 */
public abstract class BeanChecker {
    public static void assertNotNull(Object obj, String errMsg) throws Throwable {
        if (obj == null) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertNotEmpty(String str, String errMsg) throws Throwable {
        if (StringUtils.isEmpty(str)) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertNotEmpty(List list, String errMsg) throws Throwable {
        if (CollectionUtils.isEmpty(list)) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertPositive(Long o, String errMsg) throws Throwable {
        if (o == null || o <= 0) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertPositive(Integer o, String errMsg) throws Throwable {
        if (o == null || o <= 0) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertPattern(String str, String pattern, String errMsg) throws Throwable {
        if (StringUtils.isEmpty(str)
                || StringUtils.isEmpty(pattern)
                || !Pattern.matches(pattern, str)) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertPattern(String str, String pattern, ErrorCode errCode) throws Throwable {
        if (StringUtils.isEmpty(str)
                || StringUtils.isEmpty(pattern)
                || !Pattern.matches(pattern, str)) {
            throw new NSException(errCode);
        }
    }

    public static void assertBefore(Date date1, Date date2, String errMsg) throws Throwable {
        if (date1 == null || date2 == null
                || !date1.before(date2)) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertContains(Integer[] arr, Integer obj, String errMsg) throws Throwable {
        if (!BeanUtil.containsInteger(arr, obj)) {
            throw new ParameterException(errMsg);
        }
    }

    public static void assertContains(Long[] arr, Long obj, String errMsg) throws Throwable {
        if (!BeanUtil.containsLong(arr, obj)) {
            throw new ParameterException(errMsg);
        }
    }
}
