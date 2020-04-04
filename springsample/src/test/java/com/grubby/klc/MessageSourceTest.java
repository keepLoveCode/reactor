package com.grubby.klc;

import com.grubby.klc.converter.Fod;
import com.grubby.klc.editor.DependOnExoticType;
import com.grubby.klc.event.MyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSourceTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void test01() {
        String hello = messageSource.getMessage("message.hello", null,Locale.getDefault());
        System.out.println(hello);
    }

    @Test
    public void testResource() {
        Resource resource = applicationContext.getResource("classpath://application.properties");
        System.out.println(resource);
    }

    @Test
    public void testEvent() {
        MyEvent myEvent = new MyEvent(new Object());
        myEvent.name = "哈哈名";
        applicationEventPublisher.publishEvent(myEvent);
    }

    @Test
    public void testPropertyEditor() {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(new DependOnExoticType());
        beanWrapper.setPropertyValue("type","哈哈明");
        Object type = beanWrapper.getPropertyValue("type");
        System.out.println(type);
    }

    @Test
    public void testConverter() {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(new Fod());
        beanWrapper.setPropertyValue("apple","哈哈明");
        Object type = beanWrapper.getPropertyValue("apple");
        System.out.println(type);
    }
}
