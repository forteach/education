package com.forteach.education.util;

import java.util.UUID;


/**
 * @Description:
 * @version: V1.0
 * @author: liu zhenming
 * @Email: 1119264845@qq.com
 * @Date: 2018-07-11 9:31
 */
public class StringUtil {

    /**
     * @Description: 获取32位的随机UUID
     * @author: liu zhenming
     * @Date: 2018/7/10 9:34
     *
     * @return
     */
    public static String getRandomUUID(){
        return String.join("", UUID.randomUUID().toString().split("-"));
    }

}
