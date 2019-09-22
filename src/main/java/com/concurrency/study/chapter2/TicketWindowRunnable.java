package com.concurrency.study.chapter2;

public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private final static int MAX_NUMBER = 100;

    @Override
    public void run() {
        while (index <= MAX_NUMBER) {
            System.out.println(Thread.currentThread() + "的号码是：" + (index++));
        }
    }
}
