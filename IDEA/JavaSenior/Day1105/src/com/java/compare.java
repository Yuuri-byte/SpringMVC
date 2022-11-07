package com.java;

import org.junit.Test;

import java.util.Arrays;

/*
comparable接口：像string，包装类，重写了compareTo(obj)方法

 */
public class compare{

    @Test
    public void test(){
        Goods[] gd = new Goods[2];
        gd[0] = new Goods("Huawei",66.9);
        gd[1] = new Goods("Apple",12.9);
        Arrays.sort(gd);
        System.out.println(Arrays.toString(gd));
    }
}
