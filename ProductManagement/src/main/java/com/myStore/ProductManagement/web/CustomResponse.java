package com.myStore.ProductManagement.web;

import java.util.List;

import org.springframework.validation.ObjectError;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
public class CustomResponse {

	private String code;
	private String message;
	private List<ObjectError> errorList;

	public CustomResponse() {
		super();
	}

	public CustomResponse(String code, String message, List<ObjectError> errorList) {
		super();
		this.code=code;
		this.message=message;
		this.errorList=errorList;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ObjectError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ObjectError> errorList) {
		this.errorList = errorList;
	}

}
