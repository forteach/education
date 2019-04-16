package com.forteach.education.test;

import com.forteach.education.util.Md5Util;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-16 09:16
 * @version: 1.0
 * @description:
 */
public class Test
{
    public static void main(String[] args) {
        System.out.println(Md5Util.macMD5("root".concat("ad12$h*%#")));
    }
}
