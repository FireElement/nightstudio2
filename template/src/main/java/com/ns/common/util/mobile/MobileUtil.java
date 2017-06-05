package com.ns.common.util.mobile;

import com.ns.common.util.constant.MobileConstant;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by xuezhucao on 15/7/28.
 */
public class MobileUtil {
    public static boolean isValidMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        return Pattern.matches(MobileConstant.PATTERN, mobile);
    }

    public static void main(String[] args) {
        boolean temp = MobileUtil.isValidMobile("11110669701");
        System.out.println(temp);
    }
}
