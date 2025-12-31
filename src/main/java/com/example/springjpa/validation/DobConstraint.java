package com.example.springjpa.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {DobVaildator.class})
public @interface DobConstraint {
    String message() default "{INVALID_AGE}";
    int  min();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
