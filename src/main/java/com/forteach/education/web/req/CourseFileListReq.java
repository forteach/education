package com.forteach.education.web.req;

import com.forteach.education.domain.FileDatum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-7 09:53
 * @Version: 1.0
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文件对象列表")
public class CourseFileListReq implements Serializable {

    @ApiModelProperty(name = "fileDatums", value = "要修改显示的文件列表")
    private List<FileDatum> fileDatums;
}
