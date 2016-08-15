package com.ns.common.mgr;

import com.ns.common.bean.PushTemplate;
import com.ns.common.dao.PushTemplateDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class PushTemplateMgr {
    private static Log logger = LogFactory.getLog(PushTemplateMgr.class);
    protected static final long UPDATE_TEMPLATES_INTERVAL = 5 * 60 * 1000L;
    protected List<PushTemplate> templates = null;
    protected Map<Long, PushTemplate> templateMap = new HashMap<>(100);
    protected CountDownLatch latch = new CountDownLatch(1);
    private PushTemplateDao dao;

    public PushTemplateMgr(PushTemplateDao dao) {
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
        t.setName("push template dao update templates");
        t.start();
    }

    private void refreshData() {
        try {
            templates = dao.findAll();
            templateMap.clear();
            if (CollectionUtils.isEmpty(templates)) {
                logger.warn("no push template got");
            } else {
                for (PushTemplate template : templates) {
                    logger.debug("get push template: " + template.getTemplateId());
                    templateMap.put(template.getTemplateId(), template);
                }
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public List<PushTemplate> getAll() throws Throwable {
        latch.await();
        return this.templates;
    }

    public PushTemplate getByTemplateId(long templateId) throws Throwable {
        latch.await();
        return templateMap.get(templateId);
    }

}
