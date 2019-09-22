package com.concurrency.study.chapter0;

//查看thread默认的命名规则
public class CreateThread {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());
        Thread t3 = new Thread("MyThread");
        Thread t4 = new Thread(() -> {
            System.out.println("Runnable...");
        });
        System.out.println(t3.getName());
        System.out.println(t4.getName());

    }
}
