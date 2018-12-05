package com.forteach.education.web.req;

import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:18
 * @Version: 1.0
 * @Description: 查询知识点关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "知识点查询")
public class KNodeReq implements Serializable {

    @ApiModelProperty(name = "selectId", value = "查询ID", dataType = "string")
    private String selectId;

    @ApiModelProperty(name = "selectType", value = "查询类型", dataType = "int", notes = "1 科目 2 章节 3 数据类型")
    private Integer selectType;

    @ApiModelProperty(name = "sortVo", value = "查讯分页对象", required = true)
    private SortVo sortVo;
}
