package com.ns.common.biz;

import com.ns.common.bean.TimerTask;
import com.ns.common.dao.TimerTaskDao;
import com.ns.common.dao.TimerTaskJdbcDao;
import com.ns.common.util.checker.TimerTaskChecker;
import com.ns.common.util.constant.TimerTaskConstant;
import com.ns.common.util.datetime.DateTimeUtil;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.gson.GsonUtil;
import com.ns.common.util.timertask.ITimerTaskHandler;
import com.ns.common.util.uuid.UuidUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuezhucao on 16/4/15.
 */
//@Service
public class TimerTaskBiz {
    private static Log logger = LogFactory.getLog(TimerTaskBiz.class);
    @Resource
    private TimerTaskDao dao;
    @Resource
    private TimerTaskJdbcDao jdbcDao;
    @Resource
    private TimerTaskChecker checker;
    @Resource
    private ITimerTaskHandler handler;

    @PostConstruct
    public void init() {
        try {
            final String processor = UuidUtil.getUuid();
            Thread t = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            jdbcDao.modifyProcessor(processor, DateTimeUtil.getNowDate(),
                                    TimerTaskConstant.PROCESS_TASK_COUNT);
                            List<TimerTask> tasks = dao.findByProcessor(processor);
                            handler.handleTimerTasks(tasks);
                            dao.deleteByProcessor(processor);
                        } catch (Throwable e) {
                            logger.warn("", e);
                        }
                        try {
                            Thread.sleep(TimerTaskConstant.PROCESS_TASK_SLEEP_INTERVAL);
                        } catch (InterruptedException e) {
                            logger.warn("", e);
                        }
                    }
                }
            };
            t.setName("timer task biz process tasks with id "  + processor);
            logger.debug("timer task biz process tasks with id "  + processor);
            t.start();
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }

    public TimerTask create(TimerTask timerTask) throws Throwable {
        checker.checkCreate(timerTask);
        if (DateTimeUtil.getNowDate().after(timerTask.getProcessTime())) {
            throw new NSException(ErrorCode.INVALID_TIMER_TASK_PROCESS_TIME);
        }
        if (MapUtils.isNotEmpty(timerTask.getParamMap())) {
            timerTask.setParam(GsonUtil.toJson(timerTask.getParamMap()));
        }
        timerTask.setCreateTime(DateTimeUtil.getNowDate());
        return dao.save(timerTask);
    }
}
