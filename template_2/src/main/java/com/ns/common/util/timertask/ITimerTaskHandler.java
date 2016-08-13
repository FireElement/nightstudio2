package com.ns.common.util.timertask;

import com.google.gson.reflect.TypeToken;
import com.ns.common.bean.TimerTask;
import com.ns.common.util.constant.TimerTaskConstant;
import com.ns.common.util.gson.GsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by xuezhucao on 16/4/15.
 */
public abstract class ITimerTaskHandler {
    private static Log logger = LogFactory.getLog(ITimerTaskHandler.class);

    public abstract void sendSms(Map<String, String> param) throws Throwable;
    public abstract void sendPush(Map<String, String> param) throws Throwable;

    public void handleTimerTasks(List<TimerTask> timerTasks) {
        if (CollectionUtils.isEmpty(timerTasks)) {
            return;
        }
        Map<String, String> paramMap;
        for (TimerTask timerTask : timerTasks) {
            paramMap = GsonUtil.fromJson(timerTask.getParam(), new TypeToken<Map<String, String>>(){});
            handleTimerTask(timerTask.getType(), paramMap);
        }
    }

    public void handleTimerTask(int type, Map<String, String> param) {
        try {
            switch (type) {
                case TimerTaskConstant.Type.SEND_SMS:
                    sendSms(param);
                    break;
                case TimerTaskConstant.Type.SEND_PUSH:
                    sendPush(param);
                    break;
                default:
                    logger.warn("invalid timer task type: " + type);
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }
}
