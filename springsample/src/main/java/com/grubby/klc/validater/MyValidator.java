package com.grubby.klc.validater;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MyValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return false;
    }

    public void validate(Object target, Errors errors) {

    }
}
