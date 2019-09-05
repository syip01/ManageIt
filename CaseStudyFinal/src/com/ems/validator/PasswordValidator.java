package com.ems.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator
	implements ConstraintValidator<PasswordConstraint, String>{
	public static final Pattern VALID_PASSWORD_REGEX =
			Pattern.compile("^[A-Za-z0-9]{4,30}$");
	public static boolean validate(String passwordStr) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
		return matcher.find();
	}
	@Override
	public void initialize(PasswordConstraint arg0 ) {
	}
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return(validate(arg0)
				&& (arg0.length() >= 4) && (arg0.length() <= 30));
	}
}
