package com.concurrency.study.chapter7;

public class ThreadsPrintNumberInTurn {

    public static void main(String[] args) {
        CountThread countThread1 = new CountThread(1);
        CountThread countThread2 = new CountThread(2);
        CountThread countThread3 = new CountThread(3);
        new Thread(countThread1, "Thread1").start();
        new Thread(countThread2, "Thread2").start();
        new Thread(countThread3, "Thread3").start();
    }

}
