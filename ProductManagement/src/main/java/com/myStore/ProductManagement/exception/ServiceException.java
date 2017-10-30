package com.myStore.ProductManagement.exception;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
public class ServiceException extends ProductManagementCustomException {

	private ServiceException() {
		//disable
	}
	
	public ServiceException(String methodName, String exceptionType) {
		this();
		super.methodName = methodName;
		super.exceptionClassName = exceptionType;
	}
	
}
