package com.concurrency.study.chapter0;

// 中断线程最好的，最受推荐的方式是，使用共享变量（shared variable）发出信号，
// 告诉线程必须停止正在运行的任务。
public class WWJ09ThreadInterruptApply extends Thread {

    // 需将线程中断信号量定义成volatile类型
    // 或将对它的一切访问封入同步的块/方法（synchronized blocks/methods）中
    volatile boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println("The Thread is running...");
            /*
             * 使用while循环模拟 sleep 方法，这里不要使用sleep，否则在阻塞时会 抛
             * InterruptedException异常而退出循环，这样while检测stop条件就不会执行，
             * 失去了意义。
             */
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1000) {
                //do something
            }
        }
        System.out.println("Thread exit under request.");
    }

    public static void main(String[] args) throws InterruptedException {
        WWJ09ThreadInterruptApply wwj09ThreadInterruptApply =
                new WWJ09ThreadInterruptApply();
        System.out.println("Starting thread.");
        wwj09ThreadInterruptApply.start();
        Thread.sleep(2000);
        System.out.println("Asking thread to stop.");
        wwj09ThreadInterruptApply.stop = true;
        Thread.sleep(2000);
        System.out.println("Stopping application.");
    }
}
