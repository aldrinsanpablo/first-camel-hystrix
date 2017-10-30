package com.myStore.ProductManagement.web;

public enum ErrorCode {

	ID("id", "error.id"),
	CODE("code","error.code"),
	NAME("name","error.name"),
	CREATEBY("createBy","error.createBy"),
	UPDATEBY("updateBy","error.updateBy");

	private String field;
	private String value;

	ErrorCode(String pfield, String pvalue) {
		field = pfield;
		value = pvalue;
	}

	public String getField() {
		return field;
	}

	public String getValue() {
		return value;
	}

}
