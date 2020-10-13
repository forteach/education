package com.forteach.education.statistics.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/21 17:32
 * @Version: v1.0
 * @Modified：统计信息柱状图
 * @Description:
 */
@Data
public class ChartColumnarVo implements Serializable {

    /**
     * 说明
     */
    private String description;
    /**
     * 键信息
     */
    private List<String> nameList;

    /**
     * 对应的数值
     */
    private List<Number> numberList;

    private List<Number> numberList2;

    public ChartColumnarVo() {
    }

    public ChartColumnarVo(String description, List<String> nameList) {
        this.description = description;
        this.nameList = nameList;
    }

    public ChartColumnarVo(String description, List<String> nameList, List<Number> numberList) {
        this.description = description;
        this.nameList = nameList;
        this.numberList = numberList;
    }

    public ChartColumnarVo(String description, List<String> nameList,
                           List<Number> numberList, List<Number> numberList2) {
        this.description = description;
        this.nameList = nameList;
        this.numberList = numberList;
        this.numberList2 = numberList2;
    }
}