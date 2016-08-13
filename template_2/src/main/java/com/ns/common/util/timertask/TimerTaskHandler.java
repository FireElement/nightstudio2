package com.ns.common.util.timertask;

import com.ns.common.biz.SmsBiz;
import com.ns.common.util.constant.SmsConstant;
import com.ns.common.util.constant.TimerTaskConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by xuezhucao on 16/4/15.
 */
@Service
public class TimerTaskHandler extends ITimerTaskHandler {
    @Resource
    private SmsBiz smsBiz;

    @Override
    public void sendSms(Map<String, String> param) throws Throwable {
        String mobile = param.get(TimerTaskConstant.ParamMapKey.MOBILE);
        long template = Long.valueOf(param.get(TimerTaskConstant.ParamMapKey.TEMPLATE));

        if (template == SmsConstant.Template.TMPLATE_1) {
            smsBiz.send(mobile, template);
        }
    }

    @Override
    public void sendPush(Map<String, String> param) throws Throwable {
        long template = Long.valueOf(param.get(TimerTaskConstant.ParamMapKey.TEMPLATE));
        //TODO
    }
}
