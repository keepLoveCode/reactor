package com.grubby.klc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 就是提供了一个简单的event bus机制而已
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    public void onApplicationEvent(MyEvent event) {
        System.out.println(event.name);
    }
}
