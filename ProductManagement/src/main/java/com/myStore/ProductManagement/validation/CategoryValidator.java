package com.myStore.ProductManagement.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.myStore.ProductManagement.model.tables.pojos.Category;
/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@Component
public class CategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		Product detail = (WarehouseProductDetail) target;
//		if (detail.getProductId() == null) {
//			errors.rejectValue("productId", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
//		}
//		if (detail.getAvailableStocks() == null) {
//			errors.rejectValue("availableStocks", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
//		}
//		if (detail.getPrice() == null) {
//			errors.rejectValue("price", null, WarehouseConstants.FIELD_REQUIRED_MESSAGE);
//		}
	}
}
