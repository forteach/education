package com.forteach.education.common.keyword;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 14:24
 */

import com.forteach.education.util.PropertiesUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description:
 * @version: V1.0
 * @author: liu zhenming
 * @Email: 1119264845@qq.com
 * @Date: 2018-07-11 13:37
 */
@Data
@Slf4j
public class WebResult implements Serializable {

    private int ret;
    private Object data;
    private String msg;

    /**
     * 成功操作 操作码默认为0   无参
     **/
    public static WebResult okResult() {
        return okResult(getOkCode(), "");
    }

    /**
     * 成功操作 操作码默认为0   只有提示码
     **/
    public static WebResult okResult(int code) {
        return okResult(getOkCode(), String.valueOf(code), "");
    }

    /**
     * 成功操作 操作码默认为0   只有返回数据
     **/
    public static WebResult okResult(Object data) {
        return okResult(getOkCode(), data);
    }

    /**
     * 成功操作 操作码默认为0   只有提示码 和 数据
     **/
    public static WebResult okResult(int code, Object data) {
        return okResult(getOkCode(), String.valueOf(code), data);
    }

    /**
     * 内部使用
     **/
    private static WebResult okResult(int code, String windowCode, Object data) {
        Map<String, String> propertiesmap = PropertiesUtil.getMapForProperties();
        WebResult wr = new WebResult();
        wr.setData(data);
        wr.setRet(code);
        wr.setMsg(propertiesmap.get(Integer.valueOf(windowCode) + "[msg]"));
        return wr;
    }

    private static Integer getOkCode() {
        Map<String, String> propertiesmap = PropertiesUtil.getMapForProperties();
        return Integer.valueOf(propertiesmap.get("0[ret]"));
    }

    /**
     * Note:新的成功弹窗提示
     * <p></p>
     *
     * @param code 错误码
     * @param data 返回对象
     * @return WebResult    返回类型
     * @author WangKai
     */
    public static WebResult okResult(int code, Object data, String[] params) {
        return backResult(getOkCode(), String.valueOf(code), data, params);
    }

    /**
     * ##### 失败 返回 ######
     * 只传入一个失败码
     **/
    public static WebResult failResult(int code) {
        return failResult(code, "");
    }
    

    public static WebResult failResult(int code, Object object) {
        Map<String, String> propertiesmap = PropertiesUtil.getMapForProperties();
        WebResult wr = new WebResult();
        wr.setRet(code);
        wr.setData(object);
        wr.setMsg(propertiesmap.get("" + code + "" + "[msg]"));
        return wr;
    }

    public static WebResult failResult(int code, Object data, String[] params) {
        return failResult(code, String.valueOf(code), data, params);
    }

    public static WebResult failException() {
        return failResult(9999);
    }

    public static WebResult failException(String eMsg) {
        WebResult wr = new WebResult();
        wr.setRet(9999);
        wr.setMsg(eMsg);
        wr.setData("");
        return wr;
    }

    /**
     * Note:新的失败弹窗提示
     * <p></p>
     *
     * @param code       错误码
     * @param windowCode 弹窗码 用于确认后跳转位置
     * @param data       返回对象
     * @return WebResult    返回类型
     * @author WangKai
     */
    private static WebResult failResult(int code, String windowCode, Object data, String[] params) {
        return backResult(code, windowCode, data, params);
    }

    private static WebResult backResult(int code, String windowCode, Object data, String[] params) {
        Map<String, String> map = PropertiesUtil.getMapForProperties();
        WebResult wr = new WebResult();
        wr.setRet(code);
        wr.setData(data);
        wr.setMsg(map.get("0[msg]"));
        return wr;
    }

}
