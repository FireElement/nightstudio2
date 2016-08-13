package com.ns.common.util.ali;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.ns.common.biz.ParamBiz;
import com.ns.common.util.constant.ParamConstant;
import com.ns.common.util.exception.errorcode.ErrorCode;
import com.ns.common.util.exception.sys.NSException;
import com.ns.common.util.exception.sys.ParameterException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xuezhucao on 15/7/21.
 */
@Service
public class OssUtil {
    private static Log logger = LogFactory.getLog(OssUtil.class);
    @Resource
    private ParamBiz paramBiz;
    protected static OSSClient client = null;
    protected static String imgBucket = null;
    protected static final Object lock = new Object();
    protected static CountDownLatch latch = new CountDownLatch(1);

    @PostConstruct
    public void init() {
        synchronized (lock) {
            if (client == null) {
                try {
                    client = new OSSClient(
                            paramBiz.getStringByName(ParamConstant.Key.OSS_ENDPOINT),
                            paramBiz.getStringByName(ParamConstant.Key.ALI_ACCESS_KEY),
                            paramBiz.getStringByName(ParamConstant.Key.ALI_SECRET));
                    imgBucket = paramBiz.getStringByName(ParamConstant.Key.OSS_BUCKET_IMG);
                } catch (Throwable e) {
                    logger.warn("", e);
                }
                latch.countDown();
            }
        }
    }

    public static OSSClient getClient() throws Throwable {
        latch.await();
        return client;
    }

    public static void putImg(String name, byte[] img) throws Throwable {
        if (StringUtils.isEmpty(name)) {
            throw new ParameterException("OSS对象名称为空");
        }
        if (img == null || img.length == 0) {
            throw new ParameterException("图像内容为空");
        }
        OSSClient client = getClient();

        InputStream is = new ByteArrayInputStream(img);

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpeg");
        meta.setContentLength(img.length);

        PutObjectResult result;
        try {
            result = client.putObject(imgBucket, name, is, meta);
        } catch (Throwable e) {
            logger.warn("", e);
            throw new NSException(ErrorCode.UPLOAD_IMG_FAIL);
        }

        logger.debug("put result: " + result.getETag());
    }

    public static void main(String[] args) {
        try {
            OssUtil.putImg("test2.txt", "test".getBytes());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
