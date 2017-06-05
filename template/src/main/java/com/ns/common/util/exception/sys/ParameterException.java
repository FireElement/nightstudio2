package com.ns.common.util.exception.sys;

import com.ns.common.util.exception.errorcode.ErrorCode;
import org.apache.commons.lang.ArrayUtils;

public class ParameterException extends NSException {
	private static final long serialVersionUID = -4898237885783517358L;

	public ParameterException () {
		super(ErrorCode.PARAMETER_EXCEPTION);
	}

	public ParameterException (String msg) {
		super(ErrorCode.PARAMETER_EXCEPTION, msg);
	}

	@Override
	public String toString() {
		if (ArrayUtils.isEmpty(this.getArgs())) {
			return super.toString() + "。";
		}
		return super.toString() + "，" + this.getArgs()[0];
	}
}
