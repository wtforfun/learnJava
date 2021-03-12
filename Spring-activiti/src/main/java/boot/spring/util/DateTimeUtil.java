package boot.spring.util;

import java.util.Date;

/**
 * 时间处理工具类
 * */
public class DateTimeUtil {

    /**
     * @param time1 时间1
     * @param time2 时间2
     * @return 如果时间1大于等于时间2  返回true 否则返回false
     * */
    public static boolean gteTime(String time1,String time2) {

        boolean flag = false;

        int res = time1.compareTo(time2);
        if (res >= 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * @param time1 时间1
     * @param time2 时间2
     * @return 如果时间1小于等于时间2  返回true 否则返回false
     * */
    public static boolean lteTime(String time1,String time2) {

        boolean flag = false;

        int res = time1.compareTo(time2);
        if (res <= 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     *
     * */

    public static boolean gteTime(Date date1,Date date2){
        return date1.getTime() >= date2.getTime();
    }

    public static boolean lteTime(Date date1,Date date2){
        return date1.getTime() <= date2.getTime();
    }
}
