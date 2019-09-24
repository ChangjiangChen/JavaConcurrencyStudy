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
        threadService.shutdown(5000);
//        threadService.shutdown(20000);
    }
}
