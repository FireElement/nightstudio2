package com.ns.common.biz;

import com.ns.common.bean.SmsTemplate;
import com.ns.common.mgr.SmsTemplateMgr;
import com.ns.common.util.constant.MqConstant;
import com.ns.common.util.constant.SmsConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import com.ns.common.util.mobile.MobileUtil;
import com.ns.common.util.mq.IMqReceiver;
import com.ns.common.util.mq.MqSender;
import com.ns.common.util.sms.ISmsSender;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送
 */
public class SmsBiz implements IMqReceiver {
    private static Log logger = LogFactory.getLog(SmsBiz.class);
    private SmsTemplateMgr mgr;
    private ParamBiz paramBiz;
    private MqSender mqSender;
    private ISmsSender smsSender;

    public SmsBiz(ParamBiz paramBiz, SmsTemplateMgr smsTemplateMgr, MqSender mqSender, ISmsSender smsSender) {
        this.mgr = smsTemplateMgr;
        this.mqSender = mqSender;
        this.smsSender = smsSender;
    }

    public void sendTemplate1() throws Throwable {
        send(1, SmsConstant.Template.TEST);
    }

    public void send(long userId, long templateId, Object... args) throws Throwable {
        send("12345678901", templateId, args);
    }

    public void send(String mobile, long templateId, Object... args) throws Throwable {
        if (StringUtils.isEmpty(mobile)) {
            throw new ParameterException("手机号为空");
        }
        if (!MobileUtil.isValidMobile(mobile)) {
            throw new NSException(ErrorCode.INVALID_MOBILE);
        }

        SmsTemplate smsTemplate = mgr.getByTemplateId(templateId);
        if (smsTemplate == null) {
            logger.warn("no template id: " + templateId);
            return;
        }

        String content = String.format(smsTemplate.getContent(), args);

        try {
            Map<String, Object> map = new HashMap<>(2);
            map.put("mobile", mobile);
            map.put("content", content);
            logger.debug(String.format("sms content is: %s", content));
            if (paramBiz.isOnline()) {
                mqSender.send(getQueue(), map);
                logger.debug(String.format("add sms to queue success, %s", mobile));
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public void send(String[] mobiles, long templateId, Object... args) throws Throwable {
        if (ArrayUtils.isEmpty(mobiles)) {
            throw new ParameterException("手机号列表为空");
        }

        SmsTemplate smsTemplate = mgr.getByTemplateId(templateId);
        if (smsTemplate == null) {
            logger.warn("no template id: " + templateId);
            return;
        }

        String content = String.format(smsTemplate.getContent(), args);

        try {
            Map<String, Object> map = new HashMap<>(2);
            map.put("mobiles", StringUtils.join(mobiles, ','));
            map.put("content", content);
            logger.debug(String.format("sms content is: %s", content));
            if (paramBiz.isOnline()) {
                mqSender.send(getQueue(), map);
                logger.debug(String.format("add sms to queue success, %s", Arrays.toString(mobiles)));
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    @Override
    public String getQueue() {
        return MqConstant.Queue.SMS;
    }

    @Override
    public void receive(Map<String, Object> obj) {
        try {
            String mobile = (String) obj.get("mobile");
            String content = (String) obj.get("content");
            if (StringUtils.isNotEmpty(mobile)) {
                smsSender.send(mobile, content);
                logger.debug(String.format("sms send success, %s, %s",
                        mobile, content));
            } else {
                String[] mobiles = StringUtils.split((String) obj.get("mobiles"), ',');
                smsSender.send(mobiles, content);
                logger.debug(String.format("sms send success, %s, %s",
                        StringUtils.join(mobiles, ','), content));
            }

        } catch (Throwable e) {
            logger.warn("", e);
        }
    }
}
