package com.grubby.reactor.event;

public class Event {

    public String message;

    public Event(String message ) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
