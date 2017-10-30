package com.myStore.Warehouse.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.myStore.Warehouse.constants.WarehouseConstants;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductOrder;

public class WarehouseProductOrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WarehouseProductOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WarehouseProductOrder order = (WarehouseProductOrder) target;
		if(order.getProductId() == null) {
			errors.rejectValue("productId", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
		}
		
		if(order.getStatus() == null) {
			errors.rejectValue("status", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
		}
		else {		
			if(!(order.getStatus().equalsIgnoreCase("OPEN") || 
					order.getStatus().equalsIgnoreCase("CANCELED") || order.getStatus().equalsIgnoreCase("CLOSED"))) {
				errors.rejectValue("status", null, "Status could be only have a status of OPEN, CANCELED, CLOSED");
			}
		}
		
		if(order.getTimestamp() == null) {
			errors.rejectValue("timestamp", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
		}
	}

}
