package com.grubby.reactor;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

/**
 * @author grubby
 *
 */
public class FluxTest {

    @Test
    public void just() {
        Flux.just("1","2",3).subscribe(System.out::println);
    }
    @Test
    public void from() {
        Flux.from(new Publisher<Long>() {
            @Override
            public void subscribe(Subscriber<? super Long> subscriber) {
                subscriber.onNext(1L);
            }
        }).subscribe(System.out::println);
    }
}
