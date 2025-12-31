package com.example.springjpa.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.instrument.ClassFileTransformer;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {StrVaildator.class})
public @interface StrConstraint {
    String message() default "{INVALID_PASSWORD}";
     int min() ;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
