package com.ns.common.biz;

import com.ns.common.util.datetime.DateTimeUtil;
import com.ns.common.util.mq.IMqReceiver;
import com.ns.common.util.mq.MqSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 16/8/13.
 */
//TODO
@Service
public class MqBiz implements IMqReceiver {
    @Resource
    MqSender sender;

    public void send() throws Throwable {
        Map<String, Object> map = new HashMap<>(1);
        map.put("a", DateTimeUtil.getDateTimeString(DateTimeUtil.getNowDate()));
        sender.send(getQueue(), map);
    }

    @Override
    public String getQueue() {
        return "SMS";
    }

    @Override
    public void receive(Map<String, Object> obj) {
        System.out.println(obj.get("a"));
    }
}
