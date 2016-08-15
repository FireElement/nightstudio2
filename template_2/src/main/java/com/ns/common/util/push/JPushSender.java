package com.ns.common.util.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.constant.PushConstant;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuezhucao on 15/9/10.
 */
@Service
public class JPushSender implements IPushSender {
    private static Log logger = LogFactory.getLog(JPushSender.class);
    @Resource
    private ParamBiz paramBiz;
    private JPushClient client1 = null;
    private CountDownLatch latch = new CountDownLatch(1);

    @PostConstruct
    public void init() {
        try {
            client1 = new JPushClient(
                    paramBiz.getStringByName(ParamConstant.Key.JPUSH_CLIENT_1_MASTER_SECRET),
                    paramBiz.getStringByName(ParamConstant.Key.JPUSH_CLIENT_1_APP_KEY),
                    paramBiz.getIntByName(ParamConstant.Key.JPUSH_MAX_RETRY_TIME));
        } catch (Throwable e) {
            logger.warn("", e);
        }
        latch.countDown();
    }

    @Override
    public void push2Client1(long userId, long type, String msg, String page, Map<String, String> params) throws Throwable {
        latch.await();
        push(client1, getPayload(userId, type, msg, page, params));
    }

    @Override
    public void push2Client1(List<Long> userIds, long type, String msg, String page, Map<String, String> params) throws Throwable {
        latch.await();
        push(client1, getPayloadForUsers(userIds, type, msg, page, params));
    }

    protected PushPayload getPayload(long userId, long type, String msg, String page, Map<String, String> params) {
        List<String> userIds = new ArrayList<String>(1);
        userIds.add(String.valueOf(userId));
        return getPayload(userIds, type, msg, page, params);
    }

    protected PushPayload getPayloadForUsers(List<Long> userIds, long type, String msg, String page, Map<String, String> params) throws Throwable {
        if (CollectionUtils.isEmpty(userIds)) {
            throw new ParameterException("用户id列表为空");
        }
        List<String> tmp = new ArrayList<String>(userIds.size());
        for (Long userId : userIds) {
            tmp.add(String.valueOf(userId));
        }
        return getPayload(tmp, type, msg, page, params);
    }

    protected PushPayload getPayload(List<String> userIds, long type, String msg, String page, Map<String, String> params) {
        Map<String, String> extras = new HashMap<String, String>(10);
        extras.put(PushConstant.PARAM_TYPE, String.valueOf(type));
        extras.put(PushConstant.PARAM_PAGE, page);
        if (MapUtils.isNotEmpty(params)) {
            extras.putAll(params);
        }
        return getPayload(userIds, msg, extras);
    }

    protected PushPayload getPayload(List<String> userIds, String msg, Map<String, String> extras) {
        Notification.Builder builder = Notification.newBuilder();
        builder.addPlatformNotification(IosNotification.newBuilder()
                .setAlert(msg)
                .addExtras(extras)
                .build());
        builder.addPlatformNotification(AndroidNotification.newBuilder()
                .setAlert(msg)
                .addExtras(extras)
                .build());

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(userIds))
                .setNotification(builder.build())
                .build();

        return payload;
    }

    protected void push(JPushClient client, PushPayload pushPayload) {
        try {
            client.sendPush(pushPayload);
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }
}
