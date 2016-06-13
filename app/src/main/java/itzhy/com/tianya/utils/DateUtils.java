package itzhy.com.tianya.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhy on 2016/6/13
 * des:
 */
public class DateUtils {


    /**
     * 根据传入的时间获得当前时间所在周的第一天和第七天日期
     *
     * @param tm       时间
     * @param firstday 周日作为周一为0，周一作为周一1。
     */
    public static List<Date> getWeek(Date tm, int firstday) {
        Calendar c = Calendar.getInstance();
        c.setTime(tm);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DATE, -1);
        }
        List<Date> list = new ArrayList<Date>();
        Calendar cf = Calendar.getInstance();
        cf.setTime(c.getTime());
        cf.set(Calendar.DAY_OF_WEEK, cf.getFirstDayOfWeek());
        cf.add(Calendar.DATE, firstday);
        Calendar ce = Calendar.getInstance();
        ce.setTime(c.getTime());
        ce.set(Calendar.DAY_OF_WEEK, cf.getFirstDayOfWeek() + 6);
        ce.add(Calendar.DATE, firstday);
        list.add(cf.getTime());
        list.add(ce.getTime());
        return list;
    }


    /**
     * 获取当前一周的所有日期  yyyy-mm-dd
     *
     * @param firstday 0:周日为开始日 , 1:周一为开始日
     */
    public static List<String> getAllWeek(Date tm, int firstday) {
        Calendar c = Calendar.getInstance();
        c.setTime(tm);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DATE, -1);
        }
        List<String> list = new ArrayList<String>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            Calendar cf = Calendar.getInstance();
            cf.setTime(c.getTime());
            cf.set(Calendar.DAY_OF_WEEK, cf.getFirstDayOfWeek() + i);
            cf.add(Calendar.DATE, firstday);

            String time = format.format(cf.getTime());
            list.add(time);
        }
        return list;
    }


    /**
     * 获取今天日期 yyyy-MM-dd
     */
    public static String getToday() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /**
     * 获取今天日期 HHmm
     */
    public static int getCurTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        String time = format.format(date);
        return Integer.valueOf(time).intValue();
    }

}
