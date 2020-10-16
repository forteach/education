package com.forteach.education.common.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/16 16:33
 * @Version: v1.0
 * @Modified：学生端查询我的信息
 * @Description:
 */
@Data
public class StudentInfoVo implements Serializable {
    private String studentId;
    private String userName;
    private String gender;
    private String idCardNo;
    private String classId;
    private String className;
    private String phone;
}