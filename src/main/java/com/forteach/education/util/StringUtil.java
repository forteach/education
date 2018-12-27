package com.forteach.education.util;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.web.vo.SortVo;

import java.util.UUID;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;


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

    /**
     * 判断是否有效,如果传递值为空就是有效的０，否则就是传递的值
     * @param sortVo
     * @return
     */
    public static String hasEmptyIsValidated (SortVo sortVo){
        return StrUtil.blankToDefault(sortVo.getIsValidated(), TAKE_EFFECT_OPEN);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
