package com.forteach.education.information.web.req.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 资讯详情查询
 *
 * @author zjw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "资讯详情查询")
public class ByIdRequest implements Serializable {

    /**
     * 主键编号.
     **/
    @ApiModelProperty(name = "id", value = "查询id", dataType = "string", required = true)
    private String id;

}