package com.forteach.education.course.dto;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */

public interface ICourseChapterListDto {

    /**
     * 课程ID
     *
     * @return
     */
    public String getCourseId();

    /**
     * 课程名称
     *
     * @return
     */
    public String getCourseName();

    /**
     * 别名
     *
     * @return
     */
    public String getAlias();

    /**
     * 课程编号
     *
     * @return
     */
//    public String getCourseNumber();

    /**
     * 备课类型
     *
     * @return
     */
//    public String getLessonPreparationType();

    /**
     * 课程封面
     *
     * @return
     */
    public String getTopPicSrc();

    /**
     * 课程简介或描述
     *
     * @return
     */
    public String getCourseDescribe();

    /**
     * 章节id
     *
     * @return
     */
    public String getChapterId();

    /**
     * 章节名字
     *
     * @return
     */
    public String getChapterName();


    /**
     * 教师名称
     *
     * @return
     */
    public String getTeacherName();

    /**
     * 教师id
     *
     * @return
     */
    public String getTeacherId();


    public String getLessonPreparationType();
}
