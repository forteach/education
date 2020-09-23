package com.forteach.education.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    /**
     * 根据正则表达式替换内容
     *
     * @auto zjw
     * @date 2015年10月14日 上午10:40:45
     */
    public static StringBuffer replaceAll(String input, String regex, String replacement) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        while (result) {
            m.appendReplacement(sb, replacement);
            result = m.find();
        }
        m.appendTail(sb);
        return sb;
    }
}
