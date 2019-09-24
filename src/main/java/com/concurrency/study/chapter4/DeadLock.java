package com.concurrency.study.chapter4;

public class DeadLock {
    private final static Object LOCK_A = new Object();
    private final static Object LOCK_B = new Object();

    public void methodA() {
        synchronized (LOCK_A) {
            try {
                System.out.println("method A start...");
                Thread.sleep(2000);
                methodB();
                System.out.println("method A finish...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void methodB() {
        synchronized (LOCK_B) {
            try {
                System.out.println("method B start...");
                Thread.sleep(2000);
                methodA();
                System.out.println("method B finish...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        Thread t1 = new Thread(() -> {
            deadLock.methodA();
        });
        Thread t2 = new Thread(() -> {
            deadLock.methodB();
        });
        t1.start();
        t2.start();
    }

    
    /**
     * 死锁的检测（DOS环境下）
     *
     * PS C:\Users\Administrator> jps
     * 11732 Launcher
     * 13252 DeadLock
     * 10552 Jps
     * 13276
     *
     *PS C:\Users\Administrator> jstack 13252
     *
     * ...
     * Found one Java-level deadlock:
     * =============================
     * "Thread-1":
     *   waiting to lock monitor 0x000000001e310d68 (object 0x000000076b6a2358, a java.lang.Object),
     *   which is held by "Thread-0"
     * "Thread-0":
     *   waiting to lock monitor 0x000000001e3133e8 (object 0x000000076b6a2368, a java.lang.Object),
     *   which is held by "Thread-1"
     *
     * Java stack information for the threads listed above:
     * ===================================================
     * "Thread-1":
     *         at com.concurrency.study.chapter4.DeadLock.methodA(DeadLock.java:10)
     *         - waiting to lock <0x000000076b6a2358> (a java.lang.Object)
     *         at com.concurrency.study.chapter4.DeadLock.methodB(DeadLock.java:25)
     *         - locked <0x000000076b6a2368> (a java.lang.Object)
     *         at com.concurrency.study.chapter4.DeadLock.lambda$main$1(DeadLock.java:39)
     *         at com.concurrency.study.chapter4.DeadLock$$Lambda$2/990368553.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     * "Thread-0":
     *         at com.concurrency.study.chapter4.DeadLock.methodB(DeadLock.java:23)
     *         - waiting to lock <0x000000076b6a2368> (a java.lang.Object)
     *         at com.concurrency.study.chapter4.DeadLock.methodA(DeadLock.java:12)
     *         - locked <0x000000076b6a2358> (a java.lang.Object)
     *         at com.concurrency.study.chapter4.DeadLock.lambda$main$0(DeadLock.java:36)
     *         at com.concurrency.study.chapter4.DeadLock$$Lambda$1/2003749087.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     *
     * Found 1 deadlock.
     */

}
