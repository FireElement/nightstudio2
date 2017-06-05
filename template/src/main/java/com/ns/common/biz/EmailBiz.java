package com.ns.common.biz;

import com.ns.common.bean.EmailTemplate;
import com.ns.common.mgr.EmailTemplateMgr;
import com.ns.common.util.constant.EmailConstant;
import com.ns.common.util.constant.MqConstant;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.mq.IMqReceiver;
import com.ns.common.util.mq.MqSender;
import com.ns.common.util.sms.ISmsSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuezhucao on 16/4/29.
 */
public class EmailBiz implements IMqReceiver {
    private static Log logger = LogFactory.getLog(EmailBiz.class);
    private EmailTemplateMgr mgr;
    private ParamBiz paramBiz;
    private MqSender mqSender;
    private ISmsSender smsSender;

    public EmailBiz(EmailTemplateMgr emailTemplateMgr, ParamBiz paramBiz,
                    MqSender mqSender, ISmsSender smsSender) {
        this.mgr = emailTemplateMgr;
        this.paramBiz = paramBiz;
        this.mqSender = mqSender;
        this.smsSender = smsSender;
    }

    public void send(long templateId, Object... args) throws Throwable {
        EmailTemplate emailTemplate = mgr.getByTemplateId(templateId);
        if (emailTemplate == null) {
            logger.warn("no template id: " + templateId);
            return;
        }

        String subject = emailTemplate.getSubject();
        String content = String.format(emailTemplate.getContent(), args);
        String receiver = emailTemplate.getReceiver();

        try {
            Map<String, Object> map = new HashMap<>(3);
            map.put("subject", subject);
            map.put("content", content);
            map.put("receiver", receiver);
            logger.debug(String.format("email subject and content is: %s, %s", subject, content));
            if (paramBiz.isOnline()) {
                mqSender.send(getQueue(), map);
                logger.debug(String.format("add email to queue success, %s", receiver));
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public void send(long templateId, List<Object> subjectArgs, List<Object> contentArgs) throws Throwable {
        EmailTemplate emailTemplate = mgr.getByTemplateId(templateId);
        if (emailTemplate == null) {
            logger.warn("no template id: " + templateId);
            return;
        }

        String subject = String.format(emailTemplate.getSubject(), subjectArgs.toArray());
        String content = String.format(emailTemplate.getContent(), contentArgs.toArray());
        String receiver = emailTemplate.getReceiver();

        try {
            Map<String, Object> map = new HashMap<>(3);
            map.put("subject", subject);
            map.put("content", content);
            map.put("receiver", receiver);
            logger.debug(String.format("email subject and content is: %s, %s", subject, content));
            if (paramBiz.isOnline()) {
                mqSender.send(getQueue(), map);
                logger.debug(String.format("add email to queue success, %s", receiver));
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    @Override
    public String getQueue() {
        return MqConstant.Queue.EMAIL;
    }

    @Override
    public void receive(Map<String, Object> obj) {
        try {
            HtmlEmail email = new HtmlEmail();

            String subject = (String) obj.get("subject");
            String content = (String) obj.get("content");
            String receiver = (String) obj.get("receiver");
            List<String> toList = Arrays.asList(receiver.split(";"));

            email.setHostName(paramBiz.getStringByName(ParamConstant.Key.EMAIL_HOST_NAME));
            email.setSmtpPort(paramBiz.getIntByName(ParamConstant.Key.SMTP_PORT));
            email.setCharset(EmailConstant.ENCODING);
            for (String to : toList) {
                email.addTo(to);
            }
            email.setFrom(paramBiz.getStringByName(ParamConstant.Key.EMAIL_FROM));
            email.setAuthenticator(new DefaultAuthenticator(
                    paramBiz.getStringByName(ParamConstant.Key.EMAIL_USER_NAME),
                    paramBiz.getStringByName(ParamConstant.Key.EMAIL_PASSWORD)));
            email.setSSLOnConnect(paramBiz.getBoolByName(ParamConstant.Key.EMAIL_USE_SSL));
            email.setSubject(subject);
            email.setMsg(content);

            email.send();
            logger.debug(String.format("email send success, %s, %s, %s",
                    receiver, subject, content));
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public static void main(String[] args) {
        try {
            HtmlEmail email = new HtmlEmail();

            String subject = "subject";
            String content = "content";
            String receiver = "cxz@katuan.net";
            List<String> toList = Arrays.asList(receiver.split(";"));

            email.setHostName("smtp.exmail.qq.com");
            email.setSmtpPort(465);
            email.setCharset(EmailConstant.ENCODING);
            for (String to : toList) {
                email.addTo(to);
            }
            email.setFrom("om@katuan.net");
            email.setAuthenticator(new DefaultAuthenticator("om@katuan.net", "Katuan@1129"));
            email.setSSLOnConnect(true);
            email.setSubject(subject);
            email.setMsg(content);

            email.send();
            logger.debug(String.format("email send success, %s, %s, %s",
                    receiver, subject, content));
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }
}
