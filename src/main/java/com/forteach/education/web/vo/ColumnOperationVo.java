package com.forteach.education.web.vo;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 17:33
 */
public interface ColumnOperationVo {

    /**
     * 动作编号
     *
     * @return
     */
    String getActId();

    /**
     * 动作名称
     *
     * @return
     */
    String getActName();

    /**
     * 是否有效
     *
     * @return
     */
    String getIsValidated();
}