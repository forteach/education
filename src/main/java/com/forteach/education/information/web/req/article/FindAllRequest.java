package com.forteach.education.information.web.req.article;

import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 16:21
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "资讯所有列表")
public class FindAllRequest implements Serializable {

    @ApiModelProperty(value = "课程Id", name = "courseId")
    private String courseId;

    @ApiModelProperty(value = "学生Id", name = "studentId")
    private String studentId;

    @ApiModelProperty(value = "本人的用户Id", name = "userId")
    private String userId;

    @ApiModelProperty(name = "type", value = "0：我发布的 1：我收藏的 2:点赞", dataType = "int")
    private String type;

    @ApiModelProperty(name = "title", value = "标题模糊查询", dataType = "string")
    private String title;

    @ApiModelProperty(value = "分页排序字段", name = "sortVo")
    private SortVo sortVo = new SortVo();
}