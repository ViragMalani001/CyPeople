package com.example.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = empUniqueIdConstraintValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface employeeUniqueIdValidation {

	public String message();
	
    // define default groups
	public Class<?>[] groups() default {};
	
    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}
