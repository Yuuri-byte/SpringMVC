package com.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
SimpleDataFormat  对Date类格式化和解析
 */
public class DateTimeTest {

    @Test
    public void test() throws ParseException {
        SimpleDateFormat smf = new SimpleDateFormat();
        Date date = new Date();
        System.out.println(date);

        //格式化
        String format = smf.format(date);
        System.out.println(format);

        //解析 格式化逆过程
        String str = "2022/11/5 下午1:21";
        Date date1 = smf.parse(str);
        System.out.println(date1);

        /* 按照指定格式 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }


}
