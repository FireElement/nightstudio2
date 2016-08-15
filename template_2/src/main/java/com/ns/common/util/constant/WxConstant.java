package com.ns.common.util.constant;

/**
 * Created by xuezhucao on 16/7/14.
 */
public interface WxConstant {
    String APP_ID = "wx28bdd69f4a52664b";
    String SECRET = "f52d68cea530ccdfafd66877a93ff591";

    interface Auth {
        int REFRESH_TOKEN_EXPIRE = 7 * 24 * 3600;
        String URL_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
        String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        String URL_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    }

    interface JsSdk {
        interface Url {
            String GET_ACCESS_TOEN = "https://api.weixin.qq.com/cgi-bin/token";
            String GET_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
            String GET_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
        }

        interface Config {
            String NONCE_STR = "katuan";
        }
    }
}
