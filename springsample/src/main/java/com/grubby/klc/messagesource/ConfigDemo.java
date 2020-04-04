package com.grubby.klc.messagesource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ConfigDemo {

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageSource()  {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setBasenames("messages");
        return resourceBundleMessageSource;
    }
}
