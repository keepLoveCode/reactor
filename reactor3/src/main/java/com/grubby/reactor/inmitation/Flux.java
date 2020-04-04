package com.grubby.reactor.inmitation;

import org.reactivestreams.Publisher;

public abstract class Flux<T> implements Publisher<T> {

    public static<T> Flux<T> just(T... arg) {
        return  new FluxArray<>(arg);
    }
}
