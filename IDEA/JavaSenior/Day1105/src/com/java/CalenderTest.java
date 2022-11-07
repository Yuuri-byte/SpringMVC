package com.java;

import org.junit.Test;

import java.time.Instant;
import java.util.Calendar;

/*
抽象类
1.创建子类对象
     OR
2.调用静态方法
 */
public class CalenderTest {

    @Test
    public void test(){
        Calendar c1 = Calendar.getInstance();
        System.out.println(c1.getClass());

        //常用方法

        //get()
        int days = c1.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //set()
        c1.set(Calendar.DAY_OF_MONTH,22);
        int days2 = c1.get(Calendar.DAY_OF_MONTH);
        System.out.println(days2);


        //add()

    }

    @Test
    public void test1(){
        Instant in = Instant.now();
        System.out.println(in);//本初子午线
    }
}
