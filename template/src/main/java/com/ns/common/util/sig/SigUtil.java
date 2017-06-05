package com.ns.common.util.sig;

import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.encrypt.EncryptUtil;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.spring.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SigUtil {
    protected static Logger logger = Logger.getLogger(SigUtil.class);
    private static ParamBiz paramBiz = null;
    private static Object lock = new Object();

    private static ParamBiz getParamBiz() throws Throwable {
        if (paramBiz == null) {
            synchronized (lock) {
                if (paramBiz == null) {
                    try {
                        paramBiz = (ParamBiz) SpringUtil.getBean(ParamBiz.class);
                    } catch (NSException e) {
                        logger.warn("", e);
                        throw e;
                    }
                }
            }
        }
        return paramBiz;
    }

    private static String getSecret() throws Throwable {
        return getParamBiz().getStringByName(ParamConstant.Key.SIG_SECRET);
    }

    private static String getIgnoreSecret() throws Throwable {
        return getParamBiz().getStringByName(ParamConstant.Key.SIG_IGNORE_SECRET);
    }

    /**
     * 根据appid、token、lol以及时间戳来生成签名
     * @param list
     * @return
     */
    public static String calSig(List<String> list) throws Throwable {
        list.add(getSecret());
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }

        return EncryptUtil.SHA1(sb.toString());
    }

    public static boolean isIgnoreSig(String sig) throws Throwable {
        if (getIgnoreSecret().equals(sig)) {
            return true;
        }
        return false;
    }

    /**
     * 验证签名: <br/>
     * 2.根据appid、token、lol以及时间戳计算一次签名;<br/>
     * 3.比较传过来的签名以及计算出的签名是否一致;
     * @param sig
     * @param list
     * @return
     */
    public static boolean isValidSig(String sig, List<String> list) throws Throwable {
        if (isIgnoreSig(sig)) {
            return true;
        }
        String calSign = calSig(list);
        if (StringUtils.equals(calSign, sig)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> srcList = new ArrayList<String>();
        srcList.add("13916158560");
        srcList.add("22222222");
        String result = null;
        try {
            result = SigUtil.calSig(srcList);
        } catch (Throwable e) {
            logger.warn("", e);
        }
        System.out.println(result);
    }

}
