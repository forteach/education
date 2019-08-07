package com.forteach.education.wechat.web.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-8 15:28
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeChatLoginResp implements Serializable {
    /**
     * token
     */
    private String token;

    /**
     * 是否绑定　0 绑定 1 未绑定
     */
    private String binding;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 学生名称
     */
    private String studentName;

    /**
     * 学生头像
     */
    private String portrait;
}
