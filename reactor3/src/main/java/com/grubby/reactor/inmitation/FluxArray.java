package com.grubby.reactor.inmitation;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FluxArray<T> extends Flux<T> {

    public T[] array;

    public FluxArray(T[] array) {
        this.array = array;
    }

    @Override
    public void subscribe(Subscriber<? super T> actual) {
        actual.onSubscribe(new FluxArraySubscription<>(array,actual));
    }

    static class FluxArraySubscription<T> implements Subscription {

        final T[] array;

        final Subscriber<T> actual;

        boolean canceled;

        int index = 0;

        FluxArraySubscription(T[] array, Subscriber<T> actual) {
            this.array = array;
            this.actual = actual;
        }
        @Override
        public void request(long n) {
            if (canceled) {
                return;
            }
            for (int i = 0; i < n && i < array.length; i++) {
                actual.onNext(array[index++]);
            }
            if (index == array.length) {
                actual.onComplete();
            }

        }

        @Override
        public void cancel() {
            canceled = true;
        }
    }

}
