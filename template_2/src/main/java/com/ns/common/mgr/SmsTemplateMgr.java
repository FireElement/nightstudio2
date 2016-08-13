package com.ns.common.mgr;

import com.ns.common.bean.SmsTemplate;
import com.ns.common.dao.SmsTemplateDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuezhucao on 16/8/13.
 */
public class SmsTemplateMgr {
    private static Log logger = LogFactory.getLog(SmsTemplateMgr.class);
    protected static final long UPDATE_TEMPLATES_INTERVAL = 5 * 60 * 1000L;
    protected List<SmsTemplate> templates = null;
    protected Map<Long, SmsTemplate> templateMap = new HashMap<>(100);
    protected CountDownLatch latch = new CountDownLatch(1);
    private SmsTemplateDao dao;

    public SmsTemplateMgr(SmsTemplateDao dao) {
        this.dao = dao;
        init();
    }

    public void init() {
        refreshData();
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(UPDATE_TEMPLATES_INTERVAL);
                    } catch (InterruptedException e) {
                        logger.warn("", e);
                    }
                    refreshData();
                }
            }
        };
        t.setName("sms template dao update templates");
        t.start();
    }

    private void refreshData() {
        try {
            templates = dao.findAll();
            templateMap.clear();
            if (CollectionUtils.isEmpty(templates)) {
                logger.warn("no sms template got");
            } else {
                for (SmsTemplate template : templates) {
                    logger.debug("get sms template: " + template.getTemplateId());
                    templateMap.put(template.getTemplateId(), template);
                }
            }
            latch.countDown();
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public List<SmsTemplate> getAll() throws Throwable {
        latch.await();
        return this.templates;
    }

    public SmsTemplate getByTemplateId(long templateId) throws Throwable {
        latch.await();
        return templateMap.get(templateId);
    }
}
