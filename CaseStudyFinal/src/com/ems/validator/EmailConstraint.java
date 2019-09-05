package com.ems.validator;

import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
	String message() default "Invalid email format";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
