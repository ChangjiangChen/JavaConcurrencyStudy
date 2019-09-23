package com.concurrency.study.chapter0;

import java.util.Optional;

//线程优先级并不能完全决定线程执行的优先顺序，只是概率增加
public class WWJ04CreateThreadWithPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }
        });
        t2.setPriority(Thread.NORM_PRIORITY);

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }
        });
        t3.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }


}
