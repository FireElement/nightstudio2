package com.ns.common.mgr;

import com.ns.common.bean.EmailTemplate;
import com.ns.common.dao.EmailTemplateDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class EmailTemplateMgr {
    private static Log logger = LogFactory.getLog(EmailTemplateMgr.class);
    protected static final long UPDATE_TEMPLATES_INTERVAL = 5 * 60 * 1000L;
    protected List<EmailTemplate> templates = null;
    protected Map<Long, EmailTemplate> templateMap = new HashMap<>(100);
    protected CountDownLatch latch = new CountDownLatch(1);
    private EmailTemplateDao dao;

    public EmailTemplateMgr(EmailTemplateDao dao) {
        this.dao = dao;
        init();
    }

    public void init() {
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
        t.setName("email template dao update templates");
        t.start();
    }

    private void refreshData() {
        try {
            templates = dao.findAll();
            templateMap.clear();
            if (CollectionUtils.isEmpty(templates)) {
                logger.warn("no email template got");
            } else {
                for (EmailTemplate template : templates) {
                    logger.debug("get email template: " + template.getTemplateId());
                    templateMap.put(template.getTemplateId(), template);
                }
            }
            latch.countDown();
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public List<EmailTemplate> getAll() throws Throwable {
        latch.await();
        return this.templates;
    }

    public EmailTemplate getByTemplateId(long templateId) throws Throwable {
        latch.await();
        return templateMap.get(templateId);
    }

}
