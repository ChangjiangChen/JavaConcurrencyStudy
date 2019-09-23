package com.concurrency.study.chapter0;

//保持程序不退
public class WWJ07CurrentThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().join();
    }
}
