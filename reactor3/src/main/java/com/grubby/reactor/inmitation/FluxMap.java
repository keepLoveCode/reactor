package com.grubby.reactor.inmitation;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;

/**
 * flux.just(Flux.t) -注册已subscribe- map -提供一个subscribe-
 *
 * @param <T>
 * @param <R>
 */
public class FluxMap<T, R> extends Flux<R> {

    private Function<T, R> mapper;

    private Flux<T> source;

    public FluxMap(Flux<T> source, Function<T, R> mapper) {
        this.mapper = mapper;
        this.source = source;
    }

    @Override
    public void subscribe(Subscriber<? super R> subscriber) {

    }

    static class MapSubscription<T, R> implements Subscription, Subscriber<T> {

        private Subscriber<R> actual;

        private Subscription upstreamOfSubscription;

        private Function<T, R> mapper;

        private boolean done;

        public MapSubscription(Subscriber actual, Function<T, R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override
        public void request(long l) {
            this.upstreamOfSubscription.request(l);
        }

        @Override
        public void cancel() {
            done = true;
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            this.upstreamOfSubscription = subscription;
            actual.onSubscribe(this);
        }

        @Override
        public void onNext(T t) {
            actual.onNext(mapper.apply(t));
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
    }

}
