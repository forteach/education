package com.forteach.education.databank.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 9:56
 * @Version: v1.0
 * @Modified：在线网盘保存列表
 * @Description:
 */
@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "on_line_disk")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文件网盘信息")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "on_line_disk", comment = "在线网盘列表")
public class OnLineDisk extends Entitys implements Serializable {

    @Id
    @ApiModelProperty(name = "id", value = "主键id", dataType = "int", required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint comment '主键Id'")
    private Long id;
    @ApiModelProperty(name = "folder", value = "文件夹名称", dataType = "string")
    @Column(name = "folder", columnDefinition = "varchar(64) comment '所属文件夹'")
    private String folder;
    @ApiModelProperty(name = "fileName", value = "文件名称", dataType = "string")
    @Column(name = "file_name", columnDefinition = "varchar(128) comment '文件名称'")
    private String fileName;

    @ApiModelProperty(name = "fileUrl", value = "文件地址Url", dataType = "string")
    @Column(name = "file_url", columnDefinition = "varchar(512) comment '文件url'")
    private String fileUrl;

//    /**
//     * 文件夹，图片文件，文档文件，视频文件，压缩文件
//     */
//    @Column(name = "fileType", columnDefinition = "varchar(32) comment '文件类型'")
//    private String fileType;

    @ApiModelProperty(name = "fileSuffix", value = "文件后缀", dataType = "string")
    @Column(name = "file_suffix", columnDefinition = "varchar(32) comment '文件后缀'")
    private String fileSuffix;
    @ApiModelProperty(name = "pId", value = "父Id", dataType = "string", required = true)
    @Column(name = "p_id", columnDefinition = "bigint DEFAULT 0 comment '父Id'")
    private Long pId;
    @ApiModelProperty(name = "userId", value = "所属的用户Id", dataType = "string", required = true)
    @Column(name = "user_id", columnDefinition = "varchar(32) comment '所属用户Id'")
    private String userId;
    @ApiModelProperty(name = "userName", value = "所属的用户名称", dataType = "string", required = true)
    @Column(name = "user_name", columnDefinition = "varchar(32) comment '所属用户名称'")
    private String userName;
}
