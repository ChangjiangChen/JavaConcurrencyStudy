package com.concurrency.study.chapter4;

public class Main {
    public static void main(String[] args) {
        final SimpleLock runnable = new SimpleLock();
        Thread t1 = new Thread(runnable, "一号窗口");
        Thread t2 = new Thread(runnable, "二号窗口");
        Thread t3 = new Thread(runnable, "三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
