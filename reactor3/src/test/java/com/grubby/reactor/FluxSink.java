package com.grubby.reactor;

import com.grubby.reactor.event.Event;
import com.grubby.reactor.event.EventSource;
import com.grubby.reactor.event.Listener;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资源池测试
 */
public class FluxSink {

    @Test
    public void testGenerate() {
        AtomicInteger count = new AtomicInteger(1);
        Flux.generate(sink->{
                sink.next(count.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (count.getAndIncrement() > 5) {
                sink.complete();
            }
        }).subscribe(System.out::println);
    }
    @Test
    public void stateGenerate() {
        Flux.generate(()->1,(count,sink)->{
            sink.next(count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count>=5) {
                sink.complete();
            }
            return count+1;
        }).subscribe(System.out::println);
    }

    @Test
    public void completeGenerate() {
        Flux.generate(()->1,(count,sink)->{
            sink.next(count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count>=5) {
                sink.complete();
            }
            return count+1;
        },e->{
            System.out.println("e : " + e);
        }).subscribe(System.out::println);
    }

    @Test
    public void createTest() throws InterruptedException {
        EventSource eventSource = new EventSource();
        Flux.generate(sink->{
            eventSource.register(new Listener() {
                @Override
                public void onEvent(Event event) {
                    sink.next(event);
                }

                @Override
                public void stop() {
                    sink.complete();
                }
            });
        }).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
        Flux.just("1","2").subscribe(e->eventSource.sendEvent(new Event(e)));
        eventSource.eventStop();
    }
}
