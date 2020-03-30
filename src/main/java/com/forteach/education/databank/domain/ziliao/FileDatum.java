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
@Data
@Entity
@Table(name = "file_datum", indexes = {@Index(columnList = "chapter_id", name = "chapter_id_index")})
@ApiModel(description = "文件数据信息", value = "文件数据")
@org.hibernate.annotations.Table(appliesTo = "file_datum", comment = "文档资料库")
@EqualsAndHashCode(callSuper = true)
public class FileDatum extends AbsDatum implements Serializable {

}
