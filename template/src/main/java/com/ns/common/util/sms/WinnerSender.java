package com.ns.common.util.sms;

import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import com.ns.common.util.exception.sys.SystemInternalException;
import com.ns.common.util.http.HttpUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuezhucao on 15/9/7.
 */
//@Service
public class WinnerSender implements ISmsSender {
    private static Log logger = LogFactory.getLog(WinnerSender.class);
    @Resource
    private ParamBiz paramBiz;
    private String URL = null;
    private String USER_CODE = null;
    private String USER_PASS = null;
    private String CHANNEL = null;
    private String MULTI_URL = null;
    private String MULTI_USER_CODE = null;
    private String MULTI_USER_PASS = null;
    private String MULTI_CHANNEL = null;
    private String SIGN = null;
    private CountDownLatch latch = new CountDownLatch(1);

    @PostConstruct
    public void init() {
        try {
            URL = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_URL);
            USER_CODE = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_USER_CODE);
            USER_PASS = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_USER_PASS);
            CHANNEL = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_CHANNEL);
            MULTI_URL = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_MULTI_URL);
            MULTI_USER_CODE = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_MULTI_USER_CODE);
            MULTI_USER_PASS = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_MULTI_USER_PASS);
            MULTI_CHANNEL = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_MULTI_CHANNEL);
            SIGN = paramBiz.getStringByName(ParamConstant.Key.SMS_WINNER_SIGN);
        } catch (Throwable e) {
            logger.warn("", e);
        }
        latch.countDown();
    }

    @Override
    public void send(String mobile, String content) throws Throwable {
        latch.await();
        if (StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(content)) {
            throw new ParameterException();
        }
        content += SIGN;
        String result = HttpUtil.getGetContent(URL,
                "userCode", USER_CODE,
                "userPass", USER_PASS,
                "DesNo", mobile,
                "Msg", content,
                "Channel", CHANNEL);
        long code;
        try {
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            result = root.getText();
            code = Long.parseLong(result);
        } catch (Throwable e) {
            logger.warn("", e);
            throw new SystemInternalException();
        }
        if (code < 0) {
            logger.warn(String.format("send '%s' to %s fail: %s", content, mobile, code));
            throw new NSException(ErrorCode.SEND_SMS_FAIL);
        }
    }

    @Override
    public void send(String[] mobiles, String content) throws Throwable {
        latch.await();
        if (ArrayUtils.isEmpty(mobiles)
            || StringUtils.isEmpty(content)) {
            throw new ParameterException();
        }
        String desNo = Arrays.toString(mobiles)
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(", ", ",");
        content += SIGN;
        String result = HttpUtil.getGetContent(MULTI_URL,
                "userCode", MULTI_USER_CODE,
                "userPass", MULTI_USER_PASS,
                "DesNo", desNo,
                "Msg", content,
                "Channel", MULTI_CHANNEL);
        long code;
        try {
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            result = root.getText();
            code = Long.parseLong(result);
        } catch (Throwable e) {
            logger.warn("", e);
            throw new SystemInternalException();
        }
        if (code < 0) {
            logger.warn(String.format("send '%s' to %s fail: %s", content, desNo, code));
            throw new NSException(ErrorCode.SEND_SMS_FAIL);
        }
    }

}
