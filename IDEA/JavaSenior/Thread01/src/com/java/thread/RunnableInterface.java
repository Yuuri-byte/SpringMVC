package com.java.thread;
/*

 */

public class RunnableInterface {
    public static void main(String[] args) {
        MThread m1 = new MThread();
        Thread t1 = new Thread(m1);
        t1.start();

    }
}
class MThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println(i);
            }
        }
    }
}
