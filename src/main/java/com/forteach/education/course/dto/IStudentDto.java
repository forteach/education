package com.forteach.education.course.dto;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 20:10
 * @version: 1.0
 * @description:
 */
public interface IStudentDto {

    /**
     * 学生id
     * @return
     */
    public String getStudentId();

    /**
     * 学生姓名
     * @return
     */
    public String getStudentName();

    /**
     * 学生头像
     * @return
     */
    public String getPortrait();

    /**
     * 班级id
     * @return
     */
    public String getClassId();

}
