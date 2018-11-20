package cn.dblearn.blog.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils
 *
 * @author bobbi
 * @date 2018/10/20 13:26
 * @email 571002217@qq.com
 * @description 日期工具类
 */
public class DateUtils {
    /**
     * 对日期的分钟进行加/减
     * @param date
     * @param minutes
     * @return
     */
    public static long addDateMinutes(Date date, int minutes){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        return calendar.getTime().getTime();
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
