package com.example.school.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value != null) {
			try {
				LocalDate.parse(value);
			} catch (DateTimeParseException e) {
				return false;
			}
		}
		
		return true;
	}


}