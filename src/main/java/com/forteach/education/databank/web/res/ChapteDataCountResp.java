package com.forteach.education.databank.web.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-3 17:09
 * @Version: 1.0
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapteDataCountResp {

//资料领域：1教案 2课件 3预习参考 4课堂参考 5授课案例、6复习参考
 private String datumArea;

 //1文档　2图册　3视频　4音频　5链接   0所有
 private String datumType;

 //文件数量
 private int dCount;

}
