package com.myStore.ProductManagement.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myStore.ProductManagement.model.tables.pojos.Product;
import com.myStore.ProductManagement.web.ErrorCode;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@Component
public class ProductValidator implements Validator {

	@Value("${valid.notnullorempty}")
	private String notNullOrEmpty;

	private Pattern codePattern = Pattern.compile("^[\\D]{2}\\-[\\d]{6}$");

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof Product) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, ErrorCode.ID.getField(), ErrorCode.ID.getValue(),
					String.format(notNullOrEmpty, ErrorCode.ID.getField()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, ErrorCode.CODE.getField(), ErrorCode.CODE.getValue(),
					String.format(notNullOrEmpty, ErrorCode.CODE.getField()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, ErrorCode.NAME.getField(), ErrorCode.NAME.getValue(),
					String.format(notNullOrEmpty, ErrorCode.NAME.getField()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, ErrorCode.CREATEBY.getField(),
					ErrorCode.CREATEBY.getValue(), String.format(notNullOrEmpty, ErrorCode.CREATEBY.getField()));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, ErrorCode.UPDATEBY.getField(),
					ErrorCode.UPDATEBY.getValue(), String.format(notNullOrEmpty, ErrorCode.UPDATEBY.getField()));
			if (!this.codePattern.matcher(((Product) target).getCode()).matches()) {
				errors.rejectValue(ErrorCode.CODE.getField(), ErrorCode.CODE.getValue(),
						"Code must follow the format: XX-NNNNNN");
			}
		} else {
			errors.reject("invalid object", "Cannot cast to unknown object type.");
		}
	}
	
}
