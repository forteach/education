package com.forteach.education.count.dto;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 16:13
 * @version: 1.0
 * @description:
 */
public interface ICourseCount {
    /**
     * 课程id
     * @return
     */
    public String getCourseId();

    /**
     * 章节id
     * @return
     */
    public String getChapterId();

    /**
     * 班级id
     */
    public Integer getClassId();

    /**
     * 练习人数
     * @return
     */
    public Integer getDrillNumber();

    /**
     * 预习人数
     * @return
     */
    public Integer getPrepareNumber();

    /**
     * 学生人数
     * @return
     */
    public Integer getStudentsNumber();

//    private String studentsNumber;

//    public void setStudentsNumber();


}
