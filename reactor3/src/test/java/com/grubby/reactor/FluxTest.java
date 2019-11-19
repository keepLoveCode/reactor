package com.grubby.reactor;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author grubby
 *
 */
public class FluxTest {

    @Test
    public void just() {
        Flux.just("1", "2", 3).subscribe(System.out::println);
    }


    // TODO: 2019-11-17 没搞懂原理
    @Test
    public void from() {
        Flux.from(new Publisher<Long>() {
            @Override
            public void subscribe(Subscriber<? super Long> subscriber) {
                subscriber.onNext(1L);
            }
        }).subscribe(System.out::println);
    }

    @Test
    public void fromArray() {
        Flux.fromArray(new String[]{"1", "2"}).subscribe(e -> System.out.println("a :" + e));
    }

    @Test
    public void fluxRange() {
        Flux.range(5,3).subscribe(System.out::println);
    }

    /**
     * 已一定周期产生一个数字序列
     *
     * @throws InterruptedException
     */
    @Test
    public void interval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(2)).subscribe(e-> System.out.println("a :" + e));
        Thread.currentThread().join();
    }

    /**
     * 发布一个信号 结束
     */
    @Test
    public void empty() {
        Flux.empty().subscribe(System.out::println);
    }

    /**
     * 信号都不发出
     */
    @Test
    public void never() {
        Flux.never().subscribe(e -> System.out.println("a" + e));
    }

    /**
     * 发布一个错误信号,同时下游不会接收
     */
    @Test
    public void error() {
        Flux.error(new Exception("hello error")).subscribe(System.out::println);
    }

    /**
     * generate只能发布一个item
     * <p>
     * 如果没complete将一直发出。需要显示调用结束信号，不然会一直发
     * <p>
     * todo List来看下
     */
    @Test
    public void generate() {
        Flux.generate(i->{
            i.next("1");
//            i.next("2"); 只能发布一个序列
            i.complete();
        }).subscribe(System.out::println);

        Flux.generate(ArrayList::new,(i,s)->{
            i.add("0");
            s.next("1");
            if (i.size() == 10) {
                s.complete();
            }
            return i;
        }).subscribe(e-> System.out.println("a " + e));
    }


    /**
     * 可以证明item都是通过next发出的
     */
    @Test
    public void create() {
        Flux.create(i->{
            i.next("1");
            i.next("2");
            i.complete();
        }).subscribe(e-> System.out.println("a " +e));
    }

    /**
     * buffer是 必须要等缓冲区满了之后才会发出数据
     */
    @Test
    public void buffer() throws InterruptedException {
        Flux.create(i -> {
            i.next("1");
            i.next("2");
            i.next("3");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {

            }
            i.next("4");
            i.complete();
        }).buffer(3).subscribe(System.out::println);
        Thread.currentThread().join();

    }

    /**
     * 至收到元素开始计算超时
     * @throws InterruptedException
     */
    @Test
    public void bufferTimeout() throws InterruptedException {
        Flux.interval(Duration.of(3, ChronoUnit.SECONDS))
                .bufferTimeout(3, Duration.of(2, ChronoUnit.SECONDS)).subscribe(System.out::println);
        Thread.currentThread().join();
    }

    /**
     * window和buffer类似，只不过返回值变为了flux
     */
    @Test
    public void window() {
        Flux.range(0,10).window(3).subscribe(i->i.subscribe(System.out::println));
    }

    /**
     * 和8的filer一样，
     * todo 搞清楚是同步还是异步的
     */
    @Test
    public void filer() {

    }

    /**
     * zip遵循木桶定理
     */
    @Test
    public void zipWith() {
        Flux.just("A","B").zipWith(Flux.just(1,2,3)).subscribe(System.out::println);
    }

    /**
     * 取元素
     */
    @Test
    public void take() {

    }

    /**
     *
     */
    @Test
    public void reduce() {
        Flux.range(1,11).reduce((x,y)->x+y).subscribe(System.out::println);
    }
}
