package com.concurrency.study.chapter7;

import java.util.Collections;

public class CountThread implements Runnable {
    private static int type = 1;
    private static int number = 0;
    final static int NUMBER_MAX = 75;
    private int threadGivenId;
    final static int TYPE_A = 1;
    final static int TYPE_B = 2;
    final static int TYPE_C = 3;
    final static Object LOCK = new Object();

    public CountThread(int threadGivenId) {
        this.threadGivenId = threadGivenId;
    }

    @Override
    public void run() {
        synchronized (LOCK) {
            try {
                while (number < NUMBER_MAX) {
                    if (type == TYPE_A && threadGivenId == TYPE_A) {
                        System.out.println(Thread.currentThread().getName() + "--" + (++number));
                        LOCK.notifyAll();
                        type = TYPE_B;
                    } else if (type == TYPE_B && threadGivenId == TYPE_B) {
                        System.out.println(Thread.currentThread().getName() + "--" + (++number));
                        LOCK.notifyAll();
                        type = TYPE_C;
                    } else if (type == TYPE_C && threadGivenId == TYPE_C) {
                        System.out.println(Thread.currentThread().getName() + "--" + (++number));
                        LOCK.notifyAll();
                        type = TYPE_A;
                    } else {
                        LOCK.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
