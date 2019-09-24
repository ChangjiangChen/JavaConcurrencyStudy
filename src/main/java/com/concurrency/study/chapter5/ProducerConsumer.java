package com.concurrency.study.chapter5;

import java.util.stream.Stream;

//多线程环境下的生产-消费问题
public class ProducerConsumer {

    private int index = 0;

    private static final Object LOCK = new Object();

    private static volatile boolean isProduced = false;

    public void consume() {
        synchronized (LOCK) {
            /**
             * 不采用该方法的原因在于：
             * wait()被唤醒之后，会继续从wait()处往下执行剩余代码，所以判断isProduced就会被跳过
             */
//            if (isProduced) {
//                // 消费index
//                // 将isProduced设置为false
//                // 将生产者唤醒
//            } else {
//                //等待生产者生产
//            }
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "--Consumer进行数据消费" + index);
            isProduced = false;
            LOCK.notifyAll();
        }
    }

    public void produce() {
        synchronized (LOCK) {
            /**
             * 与consume同理，不采用if判断
             */
//            if (isProduced) {
//                //等待消费者消费
//            } else {
//                //生产数据
//                //将isProduced设置为true
//                //将消费者唤醒
//            }
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "--Producer进行数据生产" + (++index));
            isProduced = true;
            LOCK.notifyAll();
        }
    }


    public static void main(String[] args) {
        /**
         * 模拟多线程生产与消费
         */
        ProducerConsumer pc = new ProducerConsumer();
        Stream.of("P1", "P2", "P3").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                    //这里是为了方便观察
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());
        Stream.of("C1", "C2", "C3").forEach(n -> new Thread(n) {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());
    }
}

/**
 * 这里总结一下wait和sleep的区别是什么
 * <p>
 * 1.sleep是Thread的方法，而wait以及成对出现的notify/notifyAll是Object的方法
 * 2.sleep在执行期间不会释放monitor锁，但是wait不会释放monitor锁并且会被放入一个wait-set
 * 3.使用sleep不需要依赖monitor锁，但是wait需要
 * 4.sleep不需要唤醒，但是wait需要
 */
