package com.oni.ebookwebapp.shared.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FormatValidator.class)
public @interface Format {
    String message() default "Parameter not matching regex format";
    String regex() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
