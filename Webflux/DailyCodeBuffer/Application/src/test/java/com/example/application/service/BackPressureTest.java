package com.example.application.service;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

    @Test
    public void testBackPressure() {
        Flux<Integer> numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("value = " + value);
                if (value == 3) {
                    cancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed!!!");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureDrop() {
        Flux<Integer> numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers
                .onBackpressureDrop(integer -> {
                    System.out.println("Dropped values = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("value = " + value);
                if (value == 3) {
                    hookOnCancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed!!!");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureBuffer() {
        Flux<Integer> numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers
                .onBackpressureBuffer(10, integer -> {
                    System.out.println("Buffered value = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!!!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureError() {
        Flux<Integer> numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers
                .onBackpressureError()
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!!!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }
}
