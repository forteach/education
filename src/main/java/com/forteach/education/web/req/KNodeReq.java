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

    @ApiModelProperty(name = "courseId", value = "科目课程ID", dataType = "string")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节ID", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "dataId", value = "资料id", dataType = "string")
    private String dataId;

    @ApiModelProperty(name = "kNodeType", dataType = "string", example = "1")
    private String kNodeType;

//    @Pattern(regexp = "^[Y, N]$", message = "是否挂载参数是Y 或 N")
//    @ApiModelProperty(name = "mount", value = "是否挂载", dataType = "string", notes = "Y 挂载　N 不挂载")
//    private String mount;

    @ApiModelProperty(name = "sortVo", value = "查讯分页对象", required = true)
    private SortVo sortVo;
}
