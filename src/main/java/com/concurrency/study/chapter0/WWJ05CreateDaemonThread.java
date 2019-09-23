package com.concurrency.study.chapter0;

// 引入：主线程做具体的服务端与客户端的交互逻辑处理，子线程心跳检测服务端与客户端的连接状态
// 主线程逻辑处理完毕，线程关闭，但子线程仍然在进行检测
// 需求：主线程处理完毕后，子线程也关闭

public class WWJ05CreateDaemonThread {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1_000);
                        System.out.println("innerThread...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            // 设置子线程为Daemon，可以保证在外部线程挂掉的时候，子线程也一起挂掉
            // 且该方法只能在start之前调用
            innerThread.setDaemon(true);

            innerThread.start();

            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("outerThread...");
        });

        t.start();
    }

}
