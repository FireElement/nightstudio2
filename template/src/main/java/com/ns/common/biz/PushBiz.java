package com.ns.common.biz;

import com.ns.common.bean.PushTemplate;
import com.ns.common.mgr.PushTemplateMgr;
import com.ns.common.util.constant.PushConstant;
import com.ns.common.util.push.IPushSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by xuezhucao on 15/8/21.
 */
public class PushBiz {
    private static Log logger = LogFactory.getLog(PushBiz.class);
    private static final int CLIENT_1 = 1;
    private ParamBiz paramBiz;
    private PushTemplateMgr mgr;
    private IPushSender sender;

    public PushBiz(ParamBiz paramBiz, PushTemplateMgr pushTemplateMgr, IPushSender sender) {
        this.paramBiz = paramBiz;
        this.mgr = pushTemplateMgr;
        this.sender = sender;
    }

    protected void push(int client, long userId, long templateId, Map<String, String> params, Object... args) throws Throwable {
        PushTemplate pushTemplate = mgr.getByTemplateId(templateId);
        if (pushTemplate == null) {
            logger.warn("no template id: " + templateId);
            return;
        }

        String content = String.format(pushTemplate.getContent(), args);

        String page = PushConstant.PAGE_MAP.get(templateId);

        logger.debug(String.format("push to user %s: %s, %s", userId, content, page));

        if (paramBiz.isOnline()) {
            switch (client) {
                case CLIENT_1:
                    sender.push2Client1(userId, templateId, content, page, params);
                    break;
            }
        }
    }

    protected void push(int client, List<Long> userIds, long templateId, Map<String, String> params, Object... args) throws Throwable {
        PushTemplate pushTemplate = mgr.getByTemplateId(templateId);
        if (pushTemplate == null) {
            logger.warn("no template id: " + templateId);
            return;
        }

        String content = String.format(pushTemplate.getContent(), args);

        String page = PushConstant.PAGE_MAP.get(templateId);

        logger.debug(String.format("push to users %s: %s, %s", Arrays.toString(userIds.toArray()), content, page));

        if (paramBiz.isOnline()) {
            switch (client) {
                case CLIENT_1:
                    sender.push2Client1(userIds, templateId, content, page, params);
                    break;
            }
        }
    }
}
