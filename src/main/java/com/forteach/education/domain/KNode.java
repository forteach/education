package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-3 16:28
 * @Version: 1.0
 * @Description:
 */
@Entity
@Table(name = "k_node")
@Data
@Builder
public class KNode {
    @Id
    @JsonView(View.SummaryExtend.class)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "知识点ID", name = "k_node_Id", dataType = "string")
    @Column(name = "k_node_Id", columnDefinition = "VARCHAR(32) COMMENT '知识点编号'")
    private String kNodeId;
}
