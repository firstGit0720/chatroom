package com.chatroom.utils;

import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
@Configuration
public class DateUtils {

    private static final String TYPE_YMD = "yyyy-MM-dd";
    private static final String TYPE_YMDT1 = "yyyy-MM-dd hh:mm:ss";
    private static final String TYPE_YMDT2 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间任意格式化
     * @param date
     * @param type 格式
     * @return
     */
    public String dateToString(Date date , String type){
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(date);
    }

    /**
     * 时间任意格式化
     * @param date
     * @return
     */
    public String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMD);
        return sdf.format(date);
    }

    /**
     * 返回年月日加12小时时间
     * @param date
     * @return
     */
    public String dateTimeToString1(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMDT1);
        return sdf.format(date);
    }
    /**
     * 返回年月日加24小时时间
     * @param date
     * @return
     */
    public String dateTimeToString2(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMDT2);
        return sdf.format(date);
    }

    /**
     * 返回data格式加12小时制时间
     * @param date
     * @return
     */
    public Date StringToDateTime1(String  date){
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMDT1);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回data格式加24小时制时间
     * @param date
     * @return
     */
    public Date StringToDateTime2(String  date){
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMDT2);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回data格式加24小时制时间
     * @param date
     * @return
     */
    public Date StringToDate(String  date){
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMD);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算年龄
     * @param date
     * @return
     */
    public int getAge(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date();
        int birtgday = Integer.parseInt(sdf.format(date).substring(0,4));
        int now = Integer.parseInt(sdf.format(nowDate).substring(0,4));
        return (now - birtgday);
    }


}
