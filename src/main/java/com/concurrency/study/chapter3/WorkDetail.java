package com.concurrency.study.chapter3;

public class WorkDetail {
    public void work() throws InterruptedException {
        //模拟复杂的耗时的工作
        Thread.sleep(10_000);
    }
}
