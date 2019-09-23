package com.concurrency.study.chapter0;

//stackSize高度依赖平台

public class WWJ03CreateThreadStackSize {

    private static int counter = 1;

    public static void main(String[] args) {

        //虚拟机栈的深度有限
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(e);
                }
            }

            private void add(int i) {
                System.out.println(counter++);
                add(i + 1);
            }
        };
        new Thread(runnable).start();

        // 理论上，JVM内存大小相同
        // stackSize越大则可创建的线程就越少；stackSize越小则可创建的线程就越多
        // stackSize高度依赖平台，一般用-Xss10M去设置
    }
}
