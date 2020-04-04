package com.grubby.klc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomConverter implements Converter<String,Apple> {
    public Apple convert(String source) {
        return new Apple(source);
    }
}
