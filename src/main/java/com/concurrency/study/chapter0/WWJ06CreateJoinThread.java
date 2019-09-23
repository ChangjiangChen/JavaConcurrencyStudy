package com.concurrency.study.chapter0;

import java.util.Optional;
import java.util.stream.IntStream;

public class WWJ06CreateJoinThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });

        Thread t2 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });

        t1.start();
        t2.start();
        //join是针对于当前线程的，所以对于t1和t2，他们依然是竞争关系，而main线程需要等到t1与t2都结束之后才执行
        t1.join();
        t2.join();

        Optional.of("All of task is done.").ifPresent(System.out::println);
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }


}
