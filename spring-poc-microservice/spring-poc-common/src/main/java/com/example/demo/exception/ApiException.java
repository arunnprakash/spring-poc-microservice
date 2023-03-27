package com.example.demo.exception;

import com.example.demo.constant.ErrorCode;

/**
 * @author __ArunPrakash__
 *
 */
public class ApiException extends RuntimeException {
	
	private ErrorCode errorCode;

	public ApiException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public static RuntimeException resourceNotFound() {
		return new ApiException(ErrorCode.NOT_FOUND);
	}
	
	public static RuntimeException resourceAlreadyExist() {
		return new ApiException(ErrorCode.ALREADY_EXIST);
	}
	
	public static RuntimeException implementationNotFound() {
		return new ApiException(ErrorCode.ALREADY_EXIST);
	}
}