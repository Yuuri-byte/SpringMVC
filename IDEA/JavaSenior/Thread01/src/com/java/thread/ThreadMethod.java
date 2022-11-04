package com.java.thread;
/*
1.start()
2.run()
3.currentThread() 静态方法
4.getName()
5.setName()
6.yield() 释放执行权
7.join() 抢占执行权，直至运行完毕
8.stop() 强制结束线程
9.sleep(long millitime)
10.isAlive()


线程的优先级，从概率上讲...1.8
1.MAX_PROORITY = 10
2.MIN_PROORITY = 1
3.NORM_PROORITY = 5
4.getPriority();
5.setPriority(int p);
 */

public class ThreadMethod {
    public static void main(String[] args) {
        NewThread t1 = new NewThread();
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        t1.start();
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println(i + "***************" + Thread.currentThread().getName());
            }
//            if (i == 20){
//                try {
//                    t1.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
        }
        System.out.println(t1.isAlive());
    }
}

class NewThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0){
//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println(i + "***************" + Thread.currentThread().getName());
            }
        }
    }
}
