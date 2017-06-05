package com.ns.common.util.exception.errorcode;

public enum ErrorCode {
	DATABASE_OPERATION_EXCEPTION("1:数据库操作失败"),
	PARAMETER_EXCEPTION("2:参数错误"),
	SYSTEM_INTERNAL_EXCEPTION("3:系统内部错误"),
	INVALID_TOKEN("4:无效的token"),
	USER_NOT_EXIST("5:用户不存在"),
	INVALID_PASSWD("6:无效的密码"),
	HTTP_EXCEPTION("7:http异常"),
	CREATE_TOKEN_FAIL("8:创建token失败"),
	GET_PARAM_FAIL("9:获取参数失败"),
	GET_LAST_APP_VERSION_FAIL("10:获取最新的app版本失败"),
	GET_LAST_APP_DOWNLOAD_URL_FAIL("11:获取最新的app下载地址失败"),
	WRONG_PASSWD("11:密码错误"),
    INVALID_SIG("12:无效的sig"),
    UPLOAD_IMG_FAIL("13:上传图片失败"),
    SEND_SMS_FAIL("14:发送短信失败"),
    INVALID_MOBILE("15:无效的手机号"),
    GET_LAST_CONFIG_VERSION_FAIL("16:获取最新的配置版本失败"),
    ALREADY_LAST_CONFIG("17:已经是最新的配置"),
    CREATE_MARK_FAIL("18:创建埋点失败"),
    MARK_NOT_EXIST("19:埋点不存在"),
    INVALID_TIMER_TASK_PROCESS_TIME("20:无效的定时任务处理时间"),
    INVALID_WX_ACCESS_TOKEN("21:无效的微信access token"),
    REFRESH_WX_ACCESS_TOKEN_FAIL("22:刷新微信access token失败"),
    CREATE_WX_ACCESS_TOKEN_FAIL("23:创建微信access token失败"),
    UNBIND_WX_OPEN_ID_AND_USER_ID_FAIL("24:解绑微信open id跟用户id失败"),
    GET_USER_ID_BY_OPEN_ID_FAIL("25:根据open id查找绑定的用户失败"),
	UNKOWN_EXCEPTION("26:未知异常");

	private String value;
	
	ErrorCode (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
