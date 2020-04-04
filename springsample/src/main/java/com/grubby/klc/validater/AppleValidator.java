package com.grubby.klc.validater;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AppleValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Apple.class.isAssignableFrom(clazz);
    }
    public void validate(Object target, Errors errors) {
        //todo
    }
}
