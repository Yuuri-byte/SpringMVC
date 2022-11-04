package com.java.thread;
/*

 */

public class ThreadExer {

    public static void main(String[] args) {
        //MT1 t1 = new MT1();
        //MT2 t2 = new MT2();
        //t1.start();
        //t2.start();

        //创建匿名子类
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 !=0){
                        System.out.println(i + "\t" + "***************" + Thread.currentThread().getName());
                    }
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 ==0){
                        System.out.println(i + "\t" + "***************" + Thread.currentThread().getName());
                    }
                }
            }
        }.start();

    }
}

class MT1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0){
                System.out.println(i + "***************" + Thread.currentThread().getName());
            }
        }
    }
}

class MT2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println(i + "***************" + Thread.currentThread().getName());
            }
        }
    }
}
