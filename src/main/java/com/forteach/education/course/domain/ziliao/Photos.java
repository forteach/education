package com.forteach.education.course.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.databank.domain.ziliao.AbsDatum;
import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;;
import java.io.Serializable;


/**
 * @Description:　图片
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 15:45
 */
@Data
@Entity
@Builder
@Table(name = "photos",indexes = {@Index(columnList = "chapter_id"),@Index(columnList = "arlits_id")})
@org.hibernate.annotations.Table(appliesTo = "photos", comment = "图片")
@ApiModel(value = "图集图片")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Photos extends AbsFile implements Serializable {

    //图集编号
    @Column(name = "arlits_id", columnDefinition = "VARCHAR(32) COMMENT '图集编号'")
    private String arlitsId;


}
