package com.forteach.education.wechat.web.vo;

import lombok.Data;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-2-18 13:50
 * @version: 1.0
 * @description:
 */
@Data
public class WxDataVo {
    private String signature;
    private String rawData;
    private String encryptedData;
    private String iv;
}
