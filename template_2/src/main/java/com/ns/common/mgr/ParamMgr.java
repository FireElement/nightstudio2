package com.ns.common.mgr;

import com.ns.common.bean.Param;
import com.ns.common.dao.ParamDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuezhucao on 16/7/11.
 */
public class ParamMgr {
    private static Log logger = LogFactory.getLog(ParamMgr.class);
    protected static final long UPDATE_PARAMS_INTERVAL = 5 * 60 * 1000L;
    protected List<Param> params = null;
    protected Map<String, Param> nameMap = new HashMap<>(100);
    protected CountDownLatch latch = new CountDownLatch(1);
    private ParamDao dao;

    public ParamMgr(ParamDao dao) {
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
                        Thread.sleep(UPDATE_PARAMS_INTERVAL);
                    } catch (InterruptedException e) {
                        logger.warn("", e);
                    }
                    refreshData();
                }
            }
        };
        t.setName("param dao update params");
        t.start();
    }

    private void refreshData() {
        try {
            params = dao.findAll();
            nameMap.clear();
            if (CollectionUtils.isEmpty(params)) {
                logger.warn("no param got");
            } else {
                for (Param param : params) {
                    logger.debug("get param: " + param.getName());
                    nameMap.put(param.getName(), param);
                }
            }
            latch.countDown();
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public List<Param> getAll() throws Throwable {
        latch.await();
        return this.params;
    }

    public Param getByName(String name) throws Throwable {
        latch.await();
        return nameMap.get(name);
    }
}
