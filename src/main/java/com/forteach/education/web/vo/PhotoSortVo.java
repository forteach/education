package com.forteach.education.web.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forteach.education.databank.domain.ziliao.PhotoDatum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "分类名称不为空")
    @ApiModelProperty(value = "分类名称", name = "sortImgName", dataType = "string", required = true)
    private String sortImgName;

    @NotNull(message = "展示方式不为空")
    @ApiModelProperty(value = "展示方式", name = "sortImgType", required = true, dataType = "int",
            allowableValues = "1, 2, 3",
            notes = "0 仅创建者可见　1 输入密码可见 2 仅组员可见 3 回答问题即可查看", example = "0")
    private Integer sortImgType;

    @JsonIgnore
    @ApiModelProperty(value = "查看密码", name = "imgPassword", dataType = "string")
    private String imgPassword;

    @NotNull(message = "封面图片不为空")
    @ApiModelProperty(value = "封面图片路径", name = "topPicSrc", dataType = "string", required = true)
    private String topPicSrc;

    @NotNull(message = "科目编号不为空")
    @ApiModelProperty(value = "科目编号", name = "courceId", dataType = "string", required = true)
    private String courceId;

    public PhotoSortVo(String sortImgName, Integer sortImgType, String imgPassword, String topPicSrc, String courceId) {
        this.sortImgName = sortImgName;
        this.sortImgType = sortImgType;
        this.imgPassword = imgPassword;
        this.topPicSrc = topPicSrc;
        this.courceId = courceId;
    }

    @Transient
    @ApiModelProperty(value = "图册对象集信息", name = "photos", dataType = "list")
    private List<PhotoDatum> photos;
}
