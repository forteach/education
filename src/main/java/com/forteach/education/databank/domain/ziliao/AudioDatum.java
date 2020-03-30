package com.forteach.education.databank.domain.ziliao;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:55
 * @Version: 1.0
 * @Description: 音频资料库
 */

@Data
@Entity
@Table(name = "audio_datum", indexes = {@Index(columnList = "chapter_id", name = "chapter_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "audio_datum", comment = "音频资料库")
@ApiModel(value = "音频资料库")
public class AudioDatum extends AbsDatum implements Serializable {


}
