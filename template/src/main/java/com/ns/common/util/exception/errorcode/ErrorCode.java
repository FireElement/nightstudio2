package com.ns.common.util.exception.errorcode;

public enum ErrorCode {
	DATABASE_OPERATION_EXCEPTION(1, "数据库操作失败"),
	PARAMETER_EXCEPTION(2, "参数错误"),
	SYSTEM_INTERNAL_EXCEPTION(3, "系统内部错误"),
	INVALID_TOKEN(4, "无效的token"),
	USER_NOT_EXIST(5, "用户不存在"),
	INVALID_PASSWD(6, "无效的密码"),
	HTTP_EXCEPTION(7, "http异常"),
	CREATE_TOKEN_FAIL(8, "创建token失败"),
	GET_PARAM_FAIL(9, "获取参数失败"),
	GET_LAST_APP_VERSION_FAIL(10, "获取最新的app版本失败"),
	GET_LAST_APP_DOWNLOAD_URL_FAIL(11, "获取最新的app下载地址失败"),
	WRONG_PASSWD(12, "密码错误"),
    INVALID_SIG(13, "无效的sig"),
    UPLOAD_IMG_FAIL(14, "上传图片失败"),
    SEND_SMS_FAIL(15, "发送短信失败"),
    INVALID_MOBILE(16, "无效的手机号"),
    GET_LAST_CONFIG_VERSION_FAIL(17, "获取最新的配置版本失败"),
    ALREADY_LAST_CONFIG(18, "已经是最新的配置"),
    CREATE_MARK_FAIL(19, "创建埋点失败"),
    MARK_NOT_EXIST(20, "埋点不存在"),
    INVALID_TIMER_TASK_PROCESS_TIME(21, "无效的定时任务处理时间"),
    INVALID_WX_ACCESS_TOKEN(22, "无效的微信access token"),
    REFRESH_WX_ACCESS_TOKEN_FAIL(23, "刷新微信access token失败"),
    CREATE_WX_ACCESS_TOKEN_FAIL(24, "创建微信access token失败"),
    UNBIND_WX_OPEN_ID_AND_USER_ID_FAIL(25, "解绑微信open id跟用户id失败"),
    GET_USER_ID_BY_OPEN_ID_FAIL(26, "根据open id查找绑定的用户失败"),
	UNKOWN_EXCEPTION(27, "未知异常");

	private int code;
	private String msg;

	ErrorCode (int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return this.msg;
	}

	@Override
	public String toString() {
		return this.msg;
	}
}
