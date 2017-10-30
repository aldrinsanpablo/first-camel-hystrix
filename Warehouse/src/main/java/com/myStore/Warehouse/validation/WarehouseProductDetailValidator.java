package com.myStore.Warehouse.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.myStore.Warehouse.constants.WarehouseConstants;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail;

public class WarehouseProductDetailValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WarehouseProductDetail.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WarehouseProductDetail detail = (WarehouseProductDetail) target;
		if(detail.getProductId() == null) {
			errors.rejectValue("productId", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
		}
		if(detail.getAvailableStocks() == null) {
			errors.rejectValue("availableStocks", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
		}
		if(detail.getPrice() == null) {
			errors.rejectValue("price", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
		}
	}

}
