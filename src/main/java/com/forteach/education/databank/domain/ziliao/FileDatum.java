package com.forteach.education.databank.domain.ziliao;

import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:28
 * @Version: 1.0
 * @Description: 文档资料库
 */
//@Builder
@Data
@Entity
@Table(name = "file_datum", indexes = {@Index(columnList = "chapter_id"),@Index(columnList = "data_id")})
@ApiModel(description = "文件数据信息", value = "文件数据")
@org.hibernate.annotations.Table(appliesTo = "file_datum", comment = "文档资料库")
@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class FileDatum extends AbsDatum implements Serializable {

}
