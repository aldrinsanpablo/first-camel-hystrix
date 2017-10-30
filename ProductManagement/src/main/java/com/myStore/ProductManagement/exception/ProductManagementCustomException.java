package com.myStore.ProductManagement.exception;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
public abstract class ProductManagementCustomException extends Exception {

	String methodName;
	String exceptionClassName;

	public String getMethodName() {
		return methodName;
	}

	public String getExceptionClassName() {
		return exceptionClassName;
	}
	
	public String getInfo() {
		return "ServiceException encountered! caller=" + this.methodName + ", exceptionClass=" + this.exceptionClassName;
	}
	
}
