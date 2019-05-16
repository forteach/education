package com.forteach.education.course.domain;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.domain.Entitys;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(name = "k_node", indexes = {@Index(columnList = "k_node_id", name = "k_node_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")})
@org.hibernate.annotations.Table(appliesTo = "k_node", comment = "知识点表")
public class KNode extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "k_node_Id", columnDefinition = "VARCHAR(32) COMMENT '知识点编号'")
    private String kNodeId;


    @Column(name = "node_name", columnDefinition = "VARCHAR(32) COMMENT '知识点名称'")
    private String nodeName;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目课程ID'")
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节ID'")
    private String chapterId;

    @Column(name = "k_node_type", columnDefinition = "int COMMENT '知识点类型2 单个知识点，1　多个知识点'")
    private int kNodeType = StrUtil.isNotBlank(String.valueOf(this.getKNodeId())) ? this.getKNodeType() : 2;
}
