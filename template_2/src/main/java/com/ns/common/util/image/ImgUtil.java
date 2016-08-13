package com.ns.common.util.image;

import com.google.gson.Gson;
import com.ns.common.util.ali.OssUtil;
import com.ns.common.util.constant.ImgConstant;
import com.ns.common.util.exception.sys.ParameterException;
import com.ns.common.util.uuid.UuidUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 15/7/21.
 */
public class ImgUtil {
    private static Log logger = LogFactory.getLog(ImgUtil.class);
    private static Gson gson = new Gson();

    public static String getImgPath(int imgType) throws Throwable {
        if (!isValidImgType(imgType)) {
            throw new ParameterException("图片类型错误" + imgType);
        }

        String[] sizes = ImgConstant.SIZE_MAP.get(imgType);
        String origSize = ImgConstant.ORIG_SIZE_MAP.get(imgType);
        Map<String, String> paths = new HashMap<String, String>(sizes.length);

        String name = imgType + ImgConstant.IMG_NAME_SPACE + UuidUtil.getUuid() + ".jpg";

        String postfix;
        for (String size : sizes) {
            if (size.equals(origSize)) {
                paths.put(size, name);
                continue;
            }
            postfix = size;
            postfix = postfix.substring(1);
            postfix = postfix.replaceAll(ImgConstant.SIZE_SPACE, "w_");
            postfix = postfix + "h";
            name = name + ImgConstant.IMG_NAME_COMPRESS_PREFIX + postfix;
            paths.put(size, name);
        }

        return gson.toJson(paths);
    }

    public static void uploadImage(String name, String image) throws Throwable {
        if (StringUtils.isEmpty(name)
            || name.contains(ImgConstant.IMG_NAME_COMPRESS_PREFIX)) {
            logger.warn("invalid image name: " + name);
            throw new ParameterException();
        }
        byte[] b = new BASE64Decoder().decodeBuffer(image);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        OssUtil.putImg(name, b);
    }

    public static boolean isValidImgType(Integer imgType) {
        if (imgType == null) {
            return false;
        }
        return ArrayUtils.contains(ImgConstant.Type.ALL, imgType);
    }

    public static void main(String[] args) {
        try {
            System.out.println(ImgUtil.getImgPath(ImgConstant.Type.DRIVING_LIC));
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }
}
