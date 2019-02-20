package com.forteach.education.course.web.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 科目
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@ApiModel(value = "科目课程")
public class RCourse implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "科目编号ID", name = "courseId", dataType = "string", example = "ff808181673e8df401673e8e49cb0000")
    private String courseId;


    @ApiModelProperty(name = "courseName", value = "科目名称", dataType = "string", example = "商务英语", required = true)
    private String courseName;

    @ApiModelProperty(name = "courseNumber", value = "科目编号", dataType = "string", example = "S123456", required = true)
    private String courseNumber;

    //授课类型是多选
    @ApiModelProperty(value = "授课类型", name = "teachingType", dataType = "int", notes = "1、录播课程 2、直播课程 3、线下课堂", example = "1,2,3,", required = true)
    private String teachingType;


    @ApiModelProperty(value = "备课类型", name = "lessonPreparationType", notes = "1、单人备课２、集体备课", required = true)
    private String LessonPreparationType;


    @ApiModelProperty(value = "封面图片路径", name = "topPicSrc", notes = "保存的是封面图片路径", example = "http://wx2.sinaimg.cn/large/006nLajtly1fk65lrevkqj30dw0dwadz.jpg")
    private String topPicSrc;

    @ApiModelProperty(name = "courseDescribe", value = "课程描述富文本", notes = "长字段富文本", example = "<p>富文本</p>")
    private String courseDescribe;

}
