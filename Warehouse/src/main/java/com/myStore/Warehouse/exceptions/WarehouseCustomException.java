package com.myStore.Warehouse.exceptions;

public class WarehouseCustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WarehouseCustomException(String message) {
		super(message);
	}
	
	public WarehouseCustomException() {
		super();
	}
}
