package com.myStore.Warehouse.response;

import java.util.List;

import org.springframework.validation.ObjectError;

public class WarehouseCustomResponse {

	private String code;
	private String message;
	private List<ObjectError> body;
	
	public WarehouseCustomResponse() {
	}
	
	public List<ObjectError> getBody() {
		return body;
	}

	public void setBody(List<ObjectError> list) {
		this.body = list;
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
}
