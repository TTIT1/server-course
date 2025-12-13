package com.example.springjpa.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.util.Objects;

public class StrVaildator implements ConstraintValidator<StrConstraint, String>{
    int min;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(Objects.isNull(value)){return false;}
        return value.length()>=min;
    }

    @Override
    public void initialize(StrConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }
}
