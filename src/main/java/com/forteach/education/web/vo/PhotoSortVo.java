package com.forteach.education.web.vo;

import com.forteach.education.domain.Photos;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 17:15
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "保存图册信息对象", description = "保存图片对像")
public class PhotoSortVo {

    @ApiModelProperty(value = "分类名称", name = "sortImgName", dataType = "string")
    private String sortImgName;

    @ApiModelProperty(value = "展示方式", name = "sortImgType", dataType = "int")
    private Integer sortImgType;

    @ApiModelProperty(value = "查看密码", name = "imgPassword", dataType = "string")
    private String imgPassword;

    @ApiModelProperty(value = "封面图片路径", name = "topPicSrc", dataType = "string")
    private String topPicSrc;

    @ApiModelProperty(value = "科目编号", name = "courceId", dataType = "string")
    private String courceId;

    @ApiModelProperty(value = "图册对象集信息", name = "photos", dataType = "list")
    private List<Photos> photos;
}
