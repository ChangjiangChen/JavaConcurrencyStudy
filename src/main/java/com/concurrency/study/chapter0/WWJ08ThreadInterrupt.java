package com.concurrency.study.chapter0;

/**
 * 线程的thread.interrupt()方法是中断线程，将会设置该线程的中断状态位，即设置为true，
 * 中断的结果线程是死亡、还是等待新的任务或是继续运行至下一步，就取决于这个程序本身。
 */

/**
 * 如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、
 * 1.5中的condition.await、以及可中断的通道上的 I/O 操作方法后可进入阻塞状态），
 * 则在线程在检查中断标示时如果发现中断标示为true，
 * 则会在这些阻塞方法（sleep、join、wait、1.5中的condition.await及可中断的通道上的 I/O 操作方法）
 * 调用处抛出InterruptedException异常，
 * 并且在抛出异常后立即将线程的中断标示位清除，即重新设置为false。
 * <p>
 * 抛出异常是为了线程从阻塞状态醒过来，并在结束线程前让程序员有足够的时间来处理中断请求。
 */
public class WWJ08ThreadInterrupt {

    public static void main(String[] args) {

        /**
         * 判断当前的线程是否被中断尽量使用Thread.currentThread().isInterrupted()
         * 如果使用Thread.interrupted()，中断状态会被清除
         */
        Thread.currentThread().interrupt();
        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.interrupted());
        }

        /**
         * 中断一个线程只是为了引起该线程的注意，被中断线程可以决定如何应对中断。
         * 某些线程非常重要，以至于它们应该不理会中断，而是在处理完抛出的异常之后继续执行，
         *
         * 但是更普遍的情况是，一个线程将把中断看作一个终止请求，这种线程的run方法遵循如下形式：
         */
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1);
                /*
                 * 不管循环里是否调用过线程阻塞的方法如sleep、join、wait，这里还是需要加上
                 * !Thread.currentThread().isInterrupted()条件，虽然抛出异常后退出了循环，显
                 * 得用阻塞的情况下是多余的，但如果调用了阻塞方法但没有阻塞时，这样会更安全、更及时。
                 */
                while (!Thread.currentThread().isInterrupted() && hasMoreWork()) {
                    //do work
                }

            } catch (InterruptedException e) {
                //线程在wait或sleep期间被中断了
            } finally {
                //线程结束前做一些清理工作
            }
        });

        /**
         * 上面是while循环在try块里，如果try在while循环里时，
         * 因该在catch块里重新设置一下中断标示，因为抛出InterruptedException异常后，中断标示位会自动清除，
         * 此时应该这样：
         */
        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() && hasMoreWork()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();//重新设置中断标示
                }
            }
        });

        /**
         * 如果你不知道抛InterruptedException异常后如何处理，那么你有如下好的建议处理方式：
         *
         * 1、在catch子句中，调用Thread.currentThread.interrupt()来设置中断状态（因为抛出异常后中断标示会被清除），
         * 让外界通过判断Thread.currentThread().isInterrupted()标示来决定是否终止线程还是继续下去
         *
         * 2、或者，更好的做法就是，不使用try来捕获这样的异常，让方法直接抛出
         */
    }

    //辅助说明方法
    private static boolean hasMoreWork() {
        return false;
    }
}
