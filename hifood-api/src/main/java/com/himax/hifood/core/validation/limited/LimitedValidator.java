package com.himax.hifood.core.validation.limited;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LimitedValidator implements ConstraintValidator<Limited, String> {
    private int min;
    private int max;

    @Override
    public void initialize(Limited constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isBlank() || value.isEmpty()){
            return false;
        }

        int number = value.length();
        return number >= this.min && number <= this.max;
    }
}
