package com.forteach.education.course.web.req;

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
@ApiModel(value = "课程添加请求对象")
public class CourseFindAllReq implements Serializable {

    @ApiModelProperty(value = "分页排序字段", name = "sortVo")
    private SortVo sortVo;

}
