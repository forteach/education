package com.forteach.education.course.dto;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-9 14:42
 * @version: 1.0
 * @description:
 */
public interface ICourseReviewDto {

    /**
     * 课程id
     */
    public String getCourseId();

    /**
     * 课程平均分
     */
    public String getAverageScore();

    /**
     * 评价人数
     *
     * @return
     */
    public Integer getReviewAmount();

    /**
     * 学生id
     *
     * @return
     */
    public String getStudentId();

    /**
     * 学生名字
     *
     * @return
     */
    public String getStudentName();

    /**
     * 学生头像
     *
     * @return
     */
    public String getPortrait();

    /**
     * 评论id
     *
     * @return
     */
    public String getReviewId();

    /**
     * 评论内容
     *
     * @return
     */
    public String getReviewDescribe();

    /**
     * 班级id
     *
     * @return
     */
    public String getClassId();

    /**
     * 班级名称
     *
     * @return
     */
    public String getClassName();

    /**
     * 评分
     *
     * @return
     */
    public Integer getScore();

    /**
     * 创建时间
     *
     * @return
     */
    public String getCreateTime();

    /**
     * 教师回复内容
     *
     * @return
     */
    public String getReply();

    /**
     * 　教师回复时间
     *
     * @return
     */
    public String getReplyTime();

    /**
     * 教师id
     *
     * @return
     */
    public String getTeacherId();

    /**
     * 教师名称
     *
     * @return
     */
    public String getTeacherName();

}
