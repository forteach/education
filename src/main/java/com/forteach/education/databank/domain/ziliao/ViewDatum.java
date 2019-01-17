package com.forteach.education.databank.domain.ziliao;


import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:44
 * @Version: 1.0
 * @Description: 视频资料库
 */
//@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "view_datum", indexes = {@Index(columnList = "chapter_id")})
@org.hibernate.annotations.Table(appliesTo = "view_datum", comment = "视频资料库")
@ApiModel(value = "视频资料信息")
//@AllArgsConstructor
//@NoArgsConstructor
public class ViewDatum extends AbsDatum implements Serializable {


}
