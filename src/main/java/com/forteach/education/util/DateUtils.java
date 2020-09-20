package com.forteach.education.util;

import java.util.Calendar;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 17:47
 * @Version: v1.0
 * @Modified：日期工具
 * @Description:
 */
public class DateUtils {

    public static int getSemesterByNow(){
        /* 如果在9、10、11、12、1月，为此学年第 2 学期，
         *     其中在9、10、11、12月为 year 学年，1月为 year-1 学年。
         * 如果在2、3、4、5、6、7、8月，为此学年第 2 学期，
         *     year-1学年。
         * 计算当前的学年学期
         */
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH )+1;
        int term = 1;
        if (month > 2 && month < 9){
            term = 2;
        }
        return term;
    }
}
