package com.forteach.education.databank.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.common.domain.Entitys;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 17:26
 * @Version: 1.0
 * @Description: 分享用户
 */
@Entity
@Data
@Table(name = "share_user", indexes = {@Index(columnList = "share_id", name = "share_id_index"), @Index(columnList = "user_id", name = "user_id_index")})
@EqualsAndHashCode(callSuper = true)
@IdClass(ShareUserFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "share_user", comment = "分享用户")
@ApiModel(value = "分享用户")
@AllArgsConstructor
@NoArgsConstructor
public class ShareUser extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "分享用户主键", hidden = true)
    private ShareUserFundPrimarykey shareUserFundPrimarykey;

    @Id
    @JsonView(View.SummaryExtend.class)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "分享编号", name = "shareId", dataType = "string")
    private String shareId;

    @JsonView(View.Summary.class)
    @NotNull(message = "成员编号不为空")
    @ApiModelProperty(value = "成员编号", name = "userId", dataType = "string", required = true)
    private String userId;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "成员名称", name = "userName", dataType = "string", required = true)
    @Column(name = "user_name", columnDefinition = "VARCHAR(60) COMMENT '成员名称'")
    private String userName;

}
