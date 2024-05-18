package com.oni.ebookwebapp.shared.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FormatValidator implements ConstraintValidator<Format, String> {

    String regex;

    @Override
    public void initialize(Format constraintAnnotation) {
        regex = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches(regex);
    }
}
