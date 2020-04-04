package com.grubby.reactor.event;

import java.util.ArrayList;
import java.util.List;

public class EventSource {

    private List<Listener> listeners;

    public EventSource() {
        this.listeners = new ArrayList<>();
    }

    public void register(Listener listener) {
        listeners.add(listener);
    }

    public void sendEvent(Event event) {
        for (Listener listener : listeners) {
            listener.onEvent(event);
        }
    }

    public void eventStop() {
        listeners.forEach(Listener::stop);
    }
}
