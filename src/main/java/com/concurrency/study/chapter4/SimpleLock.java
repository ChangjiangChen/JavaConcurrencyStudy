package com.concurrency.study.chapter4;

public class SimpleLock implements Runnable {

    private int index = 1;
    private final static int MAX = 500;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (MONITOR) {
                if (index > MAX) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            }
        }
    }

    //也可以将run方法修饰为synchronized，这时多线程使用的锁是this

    /**
     * 这两种方法都用synchronized关键字将run部分锁了起来，不管是使用this还是Monitor，
     * 都会将这个部分本应该多线程执行的部分变成了串行执行，效率太低。
     */
}
