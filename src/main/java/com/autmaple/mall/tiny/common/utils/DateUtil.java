package com.autmaple.mall.tiny.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description 日期工具类
 * @Author AutMaple
 * @Date 2022/7/23 10:57
 * @Version 1.0
 **/
public class DateUtil {
    public static Date getDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
