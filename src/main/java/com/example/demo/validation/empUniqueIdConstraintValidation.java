package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class empUniqueIdConstraintValidation implements ConstraintValidator<employeeUniqueIdValidation, String>{

	@Override
	public void initialize(employeeUniqueIdValidation constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}



	

}
