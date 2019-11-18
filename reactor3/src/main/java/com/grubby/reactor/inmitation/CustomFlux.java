package com.grubby.reactor.inmitation;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CustomFlux {
    public static void main(String[] args) {
        Flux.just("1","2").subscribe(new Subscriber<String>() {
            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {
                subscription = s;
                subscription.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println("a : " + s);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t);
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                subscription.cancel();
            }
        });
    }
}
