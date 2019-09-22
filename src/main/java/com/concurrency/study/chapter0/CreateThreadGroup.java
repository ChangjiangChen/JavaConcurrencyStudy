package com.concurrency.study.chapter0;

import java.util.Arrays;

public class CreateThreadGroup {

    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        System.out.println(Thread.currentThread().getThreadGroup());
        System.out.println(t.getThreadGroup());
        System.out.println("==========================");
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
//        for (int i = 0; i < threads.length; i++) {
//            System.out.println(threads[i]);
//        }
        Arrays.asList(threads).forEach(System.out::println);
    }

}
