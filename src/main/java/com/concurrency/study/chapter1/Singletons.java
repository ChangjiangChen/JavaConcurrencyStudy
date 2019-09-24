package com.concurrency.study.chapter1;


public class Singletons {

    //饿汉式
    static class Singleton1 {
        private final static Singleton1 instance = new Singleton1();

        private Singleton1() {
        }

        public static Singleton1 getInstance() {
            return instance;
        }
    }

    //懒汉式，注意普通的双判空模式中可能出现的问题
    static class Singleton2 {

        private static volatile Singleton2 instance;

        private Singleton2() {
        }

        public static Singleton2 getInstance() {
            if (instance == null) {
                synchronized (Singleton2.class) {
                    if (instance == null) {
                        instance = new Singleton2();
                    }
                }
            }
            return instance;
        }
    }

    //内部类解决懒启动与重排序的问题，静态内部类只有在使用时才会被加载
    static class Singleton3 {
        private Singleton3() {
        }

        private static class InstanceHolder {
            private final static Singleton3 instance = new Singleton3();
        }

        public static Singleton3 getInstance() {
            return InstanceHolder.instance;
        }
    }

    //枚举类型线程安全，且构造函数只会被装载一次
    static class Singleton4 {
        private Singleton4() {
        }

        private enum SingletonEnum {
            INSTANCE;

            private final Singleton4 instance;

            SingletonEnum() {
                instance = new Singleton4();
            }

            public Singleton4 getInstance() {
                return instance;
            }
        }

        public static Singleton4 getInstance() {
            return SingletonEnum.INSTANCE.getInstance();
        }
    }

}
