package com.busybird.mytest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WuSihai on 2017/3/31.
 */

public class DateTest {
    public static void main(String[] args){
        String dataStr = "1990年12月12日 11:11:11";
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyy年MM月dd日");
        try
        {//把符合格式的时间文本解析成Date对象
            Date parse = simpleDateFormat.parse(dataStr);
            System.out.println(parse);
            System.out.println(parse.getTime());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
