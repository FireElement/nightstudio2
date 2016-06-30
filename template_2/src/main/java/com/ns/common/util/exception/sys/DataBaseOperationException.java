package com.ns.common.util.exception.sys;


import com.ns.common.util.exception.errorcode.ErrorCode;

public class DataBaseOperationException extends NSException {
	private static final long serialVersionUID = -1722989773154130594L;
	
	public DataBaseOperationException () {
		super(ErrorCode.DATABASE_OPERATION_EXCEPTION);
	}
}
