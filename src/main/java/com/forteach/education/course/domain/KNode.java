package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-3 16:28
 * @Version: 1.0
 * @Description:
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "k_node", indexes = {@Index(columnList = "k_node_id"), @Index(columnList = "course_id")})
@org.hibernate.annotations.Table(appliesTo = "k_node", comment = "知识点表")
@ApiModel(value = "知识点表数据")
public class KNode extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "k_node_Id", value = "知识点ID", dataType = "string")
    @Column(name = "k_node_Id", columnDefinition = "VARCHAR(32) COMMENT '知识点编号'")
    private String kNodeId;

    @NotBlank(message = "知识点名称不为空")
    @ApiModelProperty(name = "nodeName", value = "知识点名称", dataType = "string", example = "计算机历史", required = true)
    @Column(name = "node_name", columnDefinition = "VARCHAR(32) COMMENT '知识点名称'")
    private String nodeName;

    @ApiModelProperty(name = "course_id", value = "科目课程ID", dataType = "string", example = "ff808181677d1d5501677d1d9f9e0000")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目课程ID'")
    private String courseId;

    @ApiModelProperty(name = "chapter_id", value = "章节ID", example = "2c91809267462308016746293a2a0002", dataType = "string")
    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节ID'")
    private String chapterId;

    //******************************************************************************
//    @ApiModelProperty(value = "知识点描述", name = "node_desc", notes = "描述知识点说明", dataType = "string", example = "计算机描述详细信息")
//    @Column(name = "node_desc", columnDefinition = "VARCHAR(256) COMMENT '知识点描述'")
//    private String nodeDesc;

//    @ApiModelProperty(name = "dataId", value = "对应的资料ID", dataType = "string", example = "ff808181674ee66201674ee6aff50000")
//    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '资料ID'")
//    private String dataId;
//
//    @ApiModelProperty(name = "kNodeType", value = "知识点类型", notes = "1 单个知识点 2 复合多个知识点", example = "1")
//    @Column(name = "k_node_type", columnDefinition = "VARCHAR(32) COMMENT '知识点类型１ 单个知识点，２　多个知识点'")
//    private String kNodeType;

}
