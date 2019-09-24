package com.concurrency.study.chapter3;

public class ThreadService {

    private Thread executorThread;
    private volatile static boolean isFinished = false;

    //传入runnable接口是为了将处理逻辑与线程调度的逻辑分离
    public void execute(Runnable runnable) {
        executorThread = new Thread(() -> {
            Thread runner = new Thread(runnable);
            //设置为外部线程的守护线程，保证外部线程退出时该线程也退出
            runner.setDaemon(true);
            System.out.println("任务开始执行。");
            runner.start();
            try {
                //注意此处join针对的是外部线程，所以当外部线程被中断时，runner线程此处会进入异常
                runner.join();
                isFinished = true;
                System.out.println("任务执行结束。");
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("任务超时，被打断。");
            }
        });
        executorThread.start();
    }

    public void shutdown(long milli) {
        long current = System.currentTimeMillis();
        while (!isFinished) {
            //任务超时，结束executorThread，其内部线程也会结束
            if (System.currentTimeMillis() - current > milli) {
                System.out.println("任务超时，需要结束");
                executorThread.interrupt();
                break;
            }
            //任务还在执行，且未超时
            try {
                executorThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
            }
        }


    }
}
