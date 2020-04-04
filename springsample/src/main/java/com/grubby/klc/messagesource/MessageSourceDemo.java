package com.grubby.klc.messagesource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceDemo {

    @Autowired
    private MessageSource messageSource;


}
