package com.forteach.education.databank.domain.ziliao;

import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 14:08
 * @Version: 1.0
 * @Description: 链接资料库
 */
//@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "link_datum", comment = "链接资料库")
@Table(name = "link_datum", indexes = {@Index(columnList = "chapter_id"),@Index(columnList = "data_id")})
@ApiModel(value = "链接资料库")
//@AllArgsConstructor
//@NoArgsConstructor
public class LinkDatum extends AbsDatum implements Serializable {

}
