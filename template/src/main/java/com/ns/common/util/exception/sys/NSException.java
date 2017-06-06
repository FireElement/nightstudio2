package com.ns.common.util.exception.sys;


import com.ns.common.util.exception.errorcode.ErrorCode;

public class NSException extends Exception {
	private ErrorCode errorCode;
	private Integer code;
	private String msg;
	private Object[] args;

	public NSException() {
		super();
	}

	public NSException(ErrorCode errorCode) {
		this(errorCode, new Object[0]);
	}

	public NSException(ErrorCode errorCode, Object arg) {
		this(errorCode, new Object[]{arg});
	}

	public NSException(ErrorCode errorCode, Object[] args) {
		super();
		this.errorCode = errorCode;
		if (errorCode != null) {
			this.code = errorCode.getCode();
			this.msg = errorCode.getMsg();
		}
		if (args == null) {
			this.args = new Object[0];
		} else {
			this.args = args;
		}
	}

	public Integer getCode() {
		return this.code;
	}

	public Object[] getArgs() {
		return this.args;
	}

	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return String.format(this.msg, this.args);
	}
}
