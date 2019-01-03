package com.forteach.education.course.web.req;

import com.forteach.education.common.web.vo.SortVo;
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
@ApiModel(value = "知识点")
public class KNodeAll implements Serializable {

    @ApiModelProperty(name = "courseId", value = "科目课程ID", dataType = "string")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节ID", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "kNodeId", value = "知识点ID", dataType = "string")
    private String kNodeId;

    @ApiModelProperty(name = "nodeName", value = "知识点名称", dataType = "string")
    private String nodeName;

}
