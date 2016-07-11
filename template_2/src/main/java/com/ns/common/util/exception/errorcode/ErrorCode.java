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
	GET_LAST_APP_DOWNLOAD_URL_FAIL("11:获取最新的app下载地址失败");

	
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
