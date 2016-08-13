package com.ns.common.util.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuezhucao on 15/7/21.
 */
public class ImgConstant {
    public static final String IMG_NAME_SPACE = "-";
    public static final String IMG_NAME_COMPRESS_PREFIX = "@";

    public interface Type {
        //用户图片
        Integer AVATAR = 1;

        //司机图片
        //身份证
        Integer CERT_LIC = 101;
        //身份证
        Integer CERT_LIC_BAK = 102;
        //驾驶证
        Integer DRIVING_LIC = 103;
        //从业资格证
        Integer EMPLOY_LIC = 104;

        //货车图片
        //车身照
        Integer TRUCK = 201;
        //行驶证
        Integer CAR_LIC = 202;
        //运营证
        Integer OP_LIC = 203;

        //货主公司图片
        //营业执照
        Integer BUSI_LIC = 301;

        //定单照片
        Integer ORDER = 401;

        //建议照片
        Integer SUGGESTION = 402;

        //Banner照片
        Integer BANNER = 501;

        Integer[] ALL = {
                AVATAR,
                CERT_LIC,
                CERT_LIC_BAK,
                DRIVING_LIC,
                EMPLOY_LIC,
                TRUCK,
                CAR_LIC,
                OP_LIC,
                BUSI_LIC,
                ORDER,
                SUGGESTION,
                BANNER
        };
    }

    public static final String SIZE_SPACE = "X";

    public static final Map<Integer, String[]> SIZE_MAP = new HashMap<Integer, String[]>(30);
    public static final Map<Integer, String> ORIG_SIZE_MAP = new HashMap<Integer, String>(30);

    static {
        SIZE_MAP.put(Type.AVATAR, new String[]{
                "O1050X1050",
                "C210X210"
        });
        SIZE_MAP.put(Type.CERT_LIC, new String[]{
                "O1080X702",
                "C216X141"
        });
        SIZE_MAP.put(Type.CERT_LIC_BAK, new String[]{
                "O1080X702",
                "C216X141"
        });
        SIZE_MAP.put(Type.DRIVING_LIC, new String[]{
                "O1080X737",
                "C216X148"
        });
        SIZE_MAP.put(Type.EMPLOY_LIC, new String[]{
                "O1080X770 ",
                "C216X154"
        });
        SIZE_MAP.put(Type.TRUCK, new String[]{
                "O1080X810",
                "C216X162"
        });
        SIZE_MAP.put(Type.CAR_LIC, new String[]{
                "O1080X737",
                "C216X148"
        });
        SIZE_MAP.put(Type.OP_LIC, new String[]{
                "O1080X770",
                "C216X154"
        });
        SIZE_MAP.put(Type.BUSI_LIC, new String[]{
                "O1080X766",
                "C216X154"
        });
        SIZE_MAP.put(Type.ORDER, new String[]{
                "O1080X810",
                "C216X162"
        });
        SIZE_MAP.put(Type.SUGGESTION, new String[]{
                "O1080X810",
                "C216X162"
        });
        SIZE_MAP.put(Type.BANNER, new String[]{
                "O1080X360"
        });

        ORIG_SIZE_MAP.put(Type.AVATAR, "O1050X1050");
        ORIG_SIZE_MAP.put(Type.CERT_LIC, "O1080X702");
        ORIG_SIZE_MAP.put(Type.CERT_LIC_BAK, "O1080X702");
        ORIG_SIZE_MAP.put(Type.DRIVING_LIC, "O1080X737");
        ORIG_SIZE_MAP.put(Type.EMPLOY_LIC, "O1080X770");
        ORIG_SIZE_MAP.put(Type.TRUCK, "O1080X810");
        ORIG_SIZE_MAP.put(Type.CAR_LIC, "O1080X737");
        ORIG_SIZE_MAP.put(Type.OP_LIC, "O1080X770");
        ORIG_SIZE_MAP.put(Type.BUSI_LIC, "O1080X766");
        ORIG_SIZE_MAP.put(Type.ORDER, "O1080X810");
        ORIG_SIZE_MAP.put(Type.SUGGESTION, "O1080X810");
        ORIG_SIZE_MAP.put(Type.BANNER, "O1080X360");
    }
}
