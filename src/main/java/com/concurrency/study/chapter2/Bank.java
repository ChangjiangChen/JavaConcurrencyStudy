package com.concurrency.study.chapter2;

public class Bank {
    //这个版本的问题在于，线程的逻辑部分与线程本身是绑定的
    //实例的数据也是分离的，所以我们不得不使用static将数据置为一份
    //Runnable接口可以解决这个分离问题

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二号");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三号");
        ticketWindow3.start();
    }
}
