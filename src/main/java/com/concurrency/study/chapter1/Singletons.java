package com.concurrency.study.chapter1;

public class Singletons {

    static class Singletons1 {
        private final static Singletons1 instance = new Singletons1();

        private Singletons1() {
        }

        public static Singletons1 getInstance() {
            return instance;
        }
    }


}
