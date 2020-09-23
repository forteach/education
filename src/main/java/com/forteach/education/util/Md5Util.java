package com.forteach.education.util;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-2-22 15:12
 * @version: 1.0
 * @description:
 */
public class Md5Util {

    /**
     * 生成macMD5加密字符串
     *
     * @param passWord
     * @return
     */
    public static String macMD5(String passWord) {
        byte[] key = passWord.getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);
        return mac.digestHex(passWord);
    }
}
