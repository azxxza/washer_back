package com.seawaterbt.ssm.core.utils;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Integer getStamp(String date_str) {
        try {
            return (int) (sdfDay.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Integer getTimeStamp(String date_str) {
        try {
            return (int) (sdfTime.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Integer getTimeStamp(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(Date date) {
        return sdfDay.format(date);
    }


    public static int getTimeStamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }


    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return sdfTime.format(date);
    }

    /**
     * 秒级别的时间戳转换 YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(long time) {
        Date date = new Date();
        date.setTime(time * 1000);
        return sdfTime.format(date);
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parse(String date, String pattern) {
        DateFormat fmt = new SimpleDateFormat(pattern);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        DateFormat fmt = new SimpleDateFormat(pattern);
        return fmt.format(date);
    }

    /**
     * 把日期转换为Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 获取当天0点0分0秒（00:00:00）
     *
     * @return
     */
    private static int getTimesMorning(long time) {
        Calendar calendar = Calendar.getInstance();
        Date beginOfDate = new Date(time * 1000);
        calendar.setTime(beginOfDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return (int) (calendar.getTime().getTime() / 1000);
    }

    /**
     * 获取第二天0点0分0秒（00:00:00）
     *
     * @param time
     * @return
     */
    private static int getNextTimesMorning(long time) {
        Calendar calendar = Calendar.getInstance();
        Date beginOfDate = new Date(time * 1000);
        calendar.setTime(beginOfDate);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return (int) (calendar.getTime().getTime() / 1000);
    }


    //获取某个日期的开始时间
    private static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    private static Date getTodayBegTime() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    private static Date getWeekBegTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }


    public static void typeDateUtil(JSONObject params) {
        Integer begTime = params.getInteger("beginTime");
        Integer endTime = params.getInteger("endTime");
        if (begTime != null || endTime != null) {
            params.put("beginTime", begTime);
            params.put("endTime", endTime);
            return;
        }
        Integer dateType = params.getInteger("dateType");
        dateType = dateType == null ? 0 : dateType;
        if (dateType == 0) { // 今日
            Integer startTime = new Long(DateUtils.getTodayBegTime().getTime() / 1000).intValue();
            Integer finishTime = DateUtils.getTimeStamp();
            begTime = startTime;
            endTime = finishTime;
        } else if (dateType == 1) { // 本周
            Integer startTime = new Long(DateUtils.getWeekBegTime().getTime() / 1000).intValue();
            Integer finishTime = DateUtils.getTimeStamp();
            begTime = startTime;
            endTime = finishTime;
        } else if (dateType == 2) { // 本月
            Integer startTime = DateUtils.getLocalMonthStart();
            Integer finishTime = DateUtils.getTimeStamp();
            begTime = startTime;
            endTime = finishTime;
        } else if (dateType == 3) { // 上月
            Integer startTime = DateUtils.getLastMonthStart();
            Integer finishTime = DateUtils.getLocalMonthStart();
            begTime = startTime;
            endTime = finishTime;
        } else if (dateType == 9) { // select下拉框不能选择，为disabled

        }
        params.put("beginTime", begTime);
        params.put("endTime", endTime);
    }

    public static void typeDateUtil2(JSONObject params) {
        Integer begTime = params.getInteger("beginTime");
        Integer endTime = params.getInteger("endTime");
        if (begTime != null || endTime != null) {
            params.put("beginTime", begTime);
            if (endTime == null) {
                endTime = DateUtils.getTimeStamp();
            }
            params.put("endTime", endTime);
            return;
        }
        Integer dateType = params.getInteger("dateType");
        if (dateType == null) {
            begTime = 0;
            endTime = DateUtils.getTimeStamp();
        } else {
            if (dateType == 0) { // 今日
                begTime = DateUtils.getTodayStart();
                endTime = DateUtils.getTimeStamp();
            } else if (dateType == 1) { // 昨日
                Integer startTime = DateUtils.getYesterdayStart();
                Integer finishTime = DateUtils.getTodayStart();
                begTime = startTime;
                endTime = finishTime;
            } else if (dateType == 2) { // 本周
                begTime = DateUtils.getLocalWeekStart();
                endTime = DateUtils.getTimeStamp();
            } else if (dateType == 3) { // 上周
                Integer startTime = DateUtils.getLastWeekStart();
                Integer finishTime = DateUtils.getLocalWeekStart();
                begTime = startTime;
                endTime = new Integer(finishTime.toString());
            } else if (dateType == 4) { // 本月
                begTime = DateUtils.getLocalMonthStart();
                endTime = DateUtils.getTimeStamp();
            } else if (dateType == 5) { // 上月
                begTime = DateUtils.getLastMonthStart();
                endTime = DateUtils.getLocalMonthStart();
            } else {
                begTime = 0;
                endTime = DateUtils.getTimeStamp();
            }
        }

        params.put("beginTime", begTime);
        params.put("endTime", endTime);
    }

    public static void typeDateUtil3(JSONObject params) {
        Integer begTime = params.getInteger("beginTime");
        Integer endTime = params.getInteger("endTime");
        if (begTime != null || endTime != null) {
            params.put("beginTime", begTime);
            params.put("endTime", endTime);
            return;
        }
        Integer dateType = params.getInteger("dateType");
        if (dateType == 1) { // 今日
            begTime = DateUtils.getTodayStart();
            endTime = DateUtils.getTimeStamp();
        }
        if (dateType == 2) { // 昨日
            Integer startTime = DateUtils.getYesterdayStart();
            Integer finishTime = DateUtils.getTodayStart();
            begTime = startTime;
            endTime = finishTime;
        } else if (dateType == 3) { // 本周
            begTime = DateUtils.getLocalWeekStart();
            endTime = DateUtils.getTimeStamp();
        } else if (dateType == 4) { // 上周
            Integer startTime = DateUtils.getLastWeekStart();
            Integer finishTime = DateUtils.getLocalWeekStart();
            begTime = startTime;
            endTime = finishTime;
        } else if (dateType == 5) { // 本月
            begTime = DateUtils.getLocalMonthStart();
            endTime = DateUtils.getTimeStamp();
        } else if (dateType == 6) { // 上月
            begTime = DateUtils.getLastMonthStart();
            endTime = DateUtils.getLocalMonthStart();
        }
        params.put("beginTime", begTime);
        params.put("endTime", endTime);
    }

    public static void initQueryDate(String type, Map<String, Object> requestBody) {
        if (requestBody.get("dateTimeBegin") != null || requestBody.get("dateTimeEnd") != null) {
            requestBody.put("beginTime", requestBody.get("dateTimeBegin"));
            requestBody.put("dateTimeEnd", requestBody.get("dateTimeEnd"));
        } else {
            DateUtils.initQueryDate2(type, requestBody);
        }
    }

    public static void initQueryDate2(String type, Map<String, Object> requestBody) {
        int dateType = Integer.valueOf(type);
        Integer beginTime = null;
        Integer endTime = null;
        if (requestBody.get("beginTime") != null) {
            if (requestBody.get("endTime") == null) {
                requestBody.put("endTime", DateUtils.getTimeStamp());
            }
            return;
        }
        if (dateType == 1) {//今日
            beginTime = DateUtils.getTodayStart();
            endTime = DateUtils.getTimeStamp();
        } else if (dateType == 2) {
            beginTime = DateUtils.getYesterdayStart();
            endTime = DateUtils.getTodayStart();
        } else if (dateType == 3) {
            beginTime = DateUtils.getLocalWeekStart();
            endTime = DateUtils.getTimeStamp();
        } else if (dateType == 4) {
            beginTime = DateUtils.getLastWeekStart();
            endTime = DateUtils.getLocalWeekStart();
        } else if (dateType == 5) {
            beginTime = DateUtils.getLocalMonthStart();
            endTime = DateUtils.getTimeStamp();
        } else if (dateType == 6) {
            beginTime = DateUtils.getLastMonthStart();
            endTime = DateUtils.getLocalMonthStart();
        }
        requestBody.put("beginTime", beginTime);
        requestBody.put("endTime", endTime);
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(long str1) {

        Date one = new Date();
        one.setTime(str1);
        Date two = new Date();
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分";
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 今日
     *
     * @return
     */
    public static int getTodayStart() {
        long time = getTodayBegTime().getTime();
        return (int) (time / 1000);
    }

    /**
     * 昨日
     *
     * @return
     */
    public static int getYesterdayStart() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (int) (c.getTimeInMillis() / 1000);
    }

    /**
     * 本周
     *
     * @return
     */
    public static int getLocalWeekStart() {
        return (int) (getThisWeekMonday(new Date()).getTime() / 1000);
    }

    /**
     * 上周
     *
     * @return
     */
    public static int getLastWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(new Date()));
        cal.add(Calendar.DATE, -7);
        return (int) (cal.getTime().getTime() / 1000);
    }

    /**
     * 本月
     *
     * @return
     */
    public static int getLocalMonthStart() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (int) (c.getTimeInMillis() / 1000);
    }

    /**
     * 上月
     *
     * @return
     */
    public static int getLastMonthStart() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (int) (c.getTimeInMillis() / 1000);
    }

    public static boolean compareTime(String s1, String s2) {

        String[] array1 = s1.split(":");
        String[] array2 = s2.split(":");

        Integer beinTime = Integer.valueOf(array1[0]) * 3600 + Integer.valueOf(array1[1]) * 60 + Integer.valueOf(array1[2]);
        Integer endTime = Integer.valueOf(array2[0]) * 3600 + Integer.valueOf(array2[1]) * 60 + Integer.valueOf(array2[2]);
        return beinTime < endTime;
    }


}
