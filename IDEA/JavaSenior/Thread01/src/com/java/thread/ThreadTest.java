package com.java.thread;
/*
多线程的创建：
方式一：继承于Thread类
1.创建子类
2.重写run() ------>  将操作写进方法体中
3. .start()

Thread.currentThread().getName()
 */

public class ThreadTest {
    public static void main(String[] args) {
        //主线程
        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();
        mt1.start();//加入线程
        mt2.start();

        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0){
                System.out.println(i + "***************" + Thread.currentThread().getName());
            }
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println(i + "***************" + Thread.currentThread().getName());
            }
        }
    }
}
