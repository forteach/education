package com.forteach.education.databank.domain.ziliao;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:44
 * @Version: 1.0
 * @Description: 视频资料库
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "view_datum", indexes = {@Index(columnList = "chapter_id", name = "chapter_id_index")})
@org.hibernate.annotations.Table(appliesTo = "view_datum", comment = "视频资料库")
@ApiModel(value = "视频资料信息")
public class ViewDatum extends AbsDatum implements Serializable {


}
