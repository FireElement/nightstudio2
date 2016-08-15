package com.ns.common.util.checker;

import com.ns.common.bean.TimerTask;
import com.ns.common.util.bean.TimerTaskUtil;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xuezhucao on 16/4/11.
 */
@Service
public class TimerTaskChecker {
    private static Log logger = LogFactory.getLog(TimerTaskChecker.class);

    public void checkCreate(TimerTask timerTask) throws Throwable {
        if (timerTask == null) {
            throw new ParameterException("定时任务为空");
        }
        if (!TimerTaskUtil.isValidType(timerTask.getType())) {
            throw new ParameterException("无效的类型");
        }
        if (timerTask.getProcessTime() == null) {
            throw new ParameterException("处理时间");
        }
    }

}
