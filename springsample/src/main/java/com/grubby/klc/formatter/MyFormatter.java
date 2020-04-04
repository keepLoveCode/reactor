package com.grubby.klc.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class MyFormatter implements Formatter<Date> {

    public Date parse(String text, Locale locale) throws ParseException {
        return null;
    }

    public String print(Date object, Locale locale) {
        return null;
    }
}
