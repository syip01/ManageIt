package com.ems.validator;

import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
	String message() default "Invalid password format: Must use 4-30 alphanumeric characters";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
