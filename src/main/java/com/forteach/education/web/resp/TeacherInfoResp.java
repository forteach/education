package com.forteach.education.web.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-5 18:17
 * @version: 1.0
 * @description:
 */
@Data
@Builder
public class TeacherInfoResp implements Serializable {

    /**
     * 教师id
     */
    private String teacherId;
    /**
     * 教师编码
     */
    private String teacherCode;

    /**
     * 教师名称
     */
    private String teacherName;
}
