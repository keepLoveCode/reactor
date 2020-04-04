package com.grubby.reactor.inmitation;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Predicate;

/**
 *
 *
 *
 * @param <T>
 */

//
public class FluxFilter<T> extends Flux<T> {

    private Predicate<T> predicate;

    private boolean done;


    @Override
    public void subscribe(Subscriber<? super T> subscriber) {

    }

    static class FilterSubscription<T> implements Subscriber<T>,Subscription{

        private Subscriber<T> actual;

        private boolean done;

        private Subscription upstreamOfSubscription;

        private Predicate<T> predicate;

        public FilterSubscription(Subscriber<T> actual,Predicate<T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            upstreamOfSubscription = subscription;
            actual.onSubscribe(this);
        }

        @Override
        public void onNext(T t) {
            if (predicate.test(t)) {
                actual.onNext(t);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            if (done) {
                return;
            }
            done = true;
            actual.onError(throwable);
        }

        @Override
        public void onComplete() {
            if (done) {
                return;
            }
            done = true;
            actual.onComplete();
        }

        @Override
        public void request(long l) {
            upstreamOfSubscription.request(l);
        }

        @Override
        public void cancel() {

        }
    }
}
