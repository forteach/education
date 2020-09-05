package com.forteach.education.databank.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 10:57
 * @Version: v1.0
 * @Modified：日程规划
 * @Description:
 */
@Data
@Entity
@Table(name = "plan_date", indexes = {
        @Index(columnList = "id", name = "id_index"),
        @Index(columnList = "open_id", name = "open_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "plan_date", comment = "用户行程日程计划")
@ApiModel(value = "微信用户行程日程")
@AllArgsConstructor
@NoArgsConstructor
public class PlanDate extends Entitys implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id'")
    private String id;

    @Column(name = "open_id", columnDefinition = "varchar(32) comment '微信openId'")
    private String openId;

    @Column(name = "content", columnDefinition = "varchar(255) comment '行程内容'")
    private String content;

    @Column(name = "content_date", columnDefinition = "varchar(32) comment '行程日期'")
    private String contentDate;
}