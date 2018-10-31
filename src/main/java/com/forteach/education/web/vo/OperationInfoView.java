package com.forteach.education.web.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 1:19
 */
@Data
public class OperationInfoView implements OperationInfoVo {

    private String colParentId;
    private String colParentName;
    private String colId;
    private String colName;
    private List<String> actIds;
}
