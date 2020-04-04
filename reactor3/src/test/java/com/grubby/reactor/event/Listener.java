package com.grubby.reactor.event;

public interface Listener {
    void onEvent(Event event);

    void stop();
}
