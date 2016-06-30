package com.ns.common.util.exception.sys;


import com.ns.common.util.exception.errorcode.ErrorCode;

public class NSException extends Exception {
	private static final long serialVersionUID = -8929714273087267405L;
	private ErrorCode errorCode;
	private String code;
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
			String value = errorCode.getValue();
			int index;
			if ((index = value.indexOf(':')) != -1) {
				code = value.substring(0, index);
				msg = value.substring(index + 1);
			} else {
				code = value;
				msg = value;
			}
		}
		if (args == null) {
			this.args = new Object[0];
		} else {
			this.args = args;
		}
	}

	public String getCode() {
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
