package com.concurrency.study.chapter0;

public class WWJ10ThreadInterruptApply2 {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(run1);
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(2000);
        System.out.println("Asking thread to stop...");
        thread.interrupt();
        Thread.sleep(2000);
        System.out.println("Application exit.");
    }


    static Runnable run1 = new Runnable() {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is running...");
                long time = System.currentTimeMillis();
                // 使用while循环模拟 sleep
                while ((System.currentTimeMillis() - time < 1000)) {
                }
            }
        }
    };
    /**
     * 如果线程被阻塞，它便不能核查共享变量，也就不能停止。
     * 这在许多情况下会发生，
     * 例如调用Object.wait()、ServerSocket.accept()和DatagramSocket.receive()时...
     * <p>
     * 他们都可能永久的阻塞线程。
     * 即使发生超时，在超时期满之前持续等待也是不可行和不适当的，
     * 所以，要使用某种机制使得线程更早地退出被阻塞的状态。
     * <p>
     * Thread.interrupt()方法不会中断一个正在运行的线程。
     * 这一方法实际上完成的是，设置线程的中断标示位，
     * 在线程受到阻塞的地方（如调用sleep、wait、join等地方）
     * 抛出一个异常InterruptedException，并且<中断状态也将被清除>，这样线程就得以退出阻塞的状态。
     */
    static Thread blockedProcessThread = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread running...");
            try {
                /*
                 * 如果线程阻塞，将不会去检查中断信号量stop变量，所 以thread.interrupt()
                 * 会使阻塞线程从阻塞的地方抛出异常，让阻塞线程从阻塞状态逃离出来，并
                 * 进行异常块进行 相应的处理
                 */
                Thread.sleep(1000);// 线程阻塞，如果线程收到中断操作信号将抛出异常
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted...");
                /*
                 * 如果线程在调用 Object.wait()方法，或者该类的 join() 、sleep()方法
                 * 过程中受阻，则其中断状态将被清除
                 */
                System.out.println(Thread.currentThread().isInterrupted());// false

                //中不中断由自己决定，如果需要真真中断线程，则需要重新设置中断位，如果
                //不需要，则不用调用
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread exiting under request...");

    });

    /**
     * 另外，死锁状态线程无法被中断
     * interrupt()方法是不能中断死锁线程的，因为锁定的位置根本无法抛出异常
     */
}
