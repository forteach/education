package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
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
//@Builder
@Table(name = "photos",indexes = {@Index(columnList = "chapter_id"),@Index(columnList = "data_id")})
@org.hibernate.annotations.Table(appliesTo = "photos", comment = "图片")
@ApiModel(value = "图集图片")
@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
public class PhotoDatum extends AbsDatum implements Serializable {


}
