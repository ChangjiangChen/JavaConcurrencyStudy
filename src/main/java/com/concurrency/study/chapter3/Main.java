package com.concurrency.study.chapter3;

public class Main {
    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        WorkDetail workDetail = new WorkDetail();
        threadService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    workDetail.work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 如果任务的执行时间比shutdown的长，那么任务会被打断
         */
        //        threadService.shutdown(5000);
        /**
         * 如果任务的执行时间比shutdown短，那么结束时刻整个App就回退出
         */
        //        threadService.shutdown(20000);
    }
}
