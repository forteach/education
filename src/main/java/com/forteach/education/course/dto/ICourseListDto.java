package com.forteach.education.course.dto;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */

public interface ICourseListDto {

    /**
     * 课程ID
     * @return
     */
    public String getCourseId();

    /**
     *课程名称
     * @return
     */
    public String getCourseName();

    /**
     *课程编号
     * @return
     */
    public String getCourseNumber();

    /**
     *备课类型
     * @return
     */
    public int getLessonPreparationType();

    /**
     *课程封面
     * @return
     */
    public String getTopPicSrc();

}