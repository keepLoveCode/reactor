package com.grubby.klc.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class MyEvent extends ApplicationEvent implements ResolvableTypeProvider {
    public String name;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }

    public ResolvableType getResolvableType() {
        return null;
    }
}
