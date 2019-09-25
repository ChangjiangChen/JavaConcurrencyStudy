package com.concurrency.study.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FixedNumberOfWorkingThread {

    private final static Object LOCK = new Object();
    private static int threadNumbers = 0;
    private static final int MAX_THREAD_NUMBER = 5;

    public static Thread getThread(String threadName) {
        return new Thread(() -> {
            synchronized (LOCK) {
                while (threadNumbers >= MAX_THREAD_NUMBER) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                threadNumbers++;
            }
            //如果工作模块被放入synchronized中，那么整个程序会变成串行
            workDetail();
            synchronized (LOCK) {
                threadNumbers--;
                LOCK.notifyAll();
            }

        }, threadName);
    }

    private static void workDetail() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始工作。");
            int simulateWorkTime = new Random().nextInt(10) * 2000;
            Thread.sleep(simulateWorkTime);
            System.out.println(Thread.currentThread().getName() + "结束工作。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        String[] machineArray = {"M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10"};
        for (int i = 0; i < machineArray.length; i++) {
            final Thread thread = getThread(machineArray[i]);
            thread.start();
            list.add(thread);
        }
        //如果在machineArray中添加join，会使所有线程变为串行（想想为什么）
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("所有工作全部结束");
    }
}
