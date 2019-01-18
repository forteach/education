package com.forteach.education.course.web.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
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
public class RCourseData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataId;

    private String datumName;

    private String datumUrl;

    private String datumArea;

    private String datumType;

    private String courseId;

    private String chapterId;

    private String kNodeId;

    private String teachShare="0";

    private String stuShare="0";

}
