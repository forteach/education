package com.forteach.education.count.dto;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 16:13
 * @version: 1.0
 * @description: 查询统计信息
 */
public interface ICourseCount {
    /**
     * 课程id
     *
     * @return
     */
    public String getCourseId();

    /**
     * 章节id
     *
     * @return
     */
    public String getChapterId();

    /**
     * 班级id
     */
    public String getClassId();

    /**
     * 学生人数
     *
     * @return
     */
    public Integer getStudentsNumber();

    /**
     * 加入的学生人数
     *
     * @return
     */
    public Integer getJoinNumber();

    /**
     * 练习人数
     *
     * @return
     */
    public Integer getDrillNumber();

    /**
     * 预习人数
     *
     * @return
     */
    public Integer getPrepareNumber();


    /**
     * 家庭作业统计人数
     *
     * @return
     */
    public Integer getHomeWorkNumber();

    /**
     * 交互统计人数
     *
     * @return
     */
    public Integer getInteractionNumber();

    /**
     * 课堂奖励统计人数
     *
     * @return
     */
    public Integer getRewardsNumber();

    /**
     * 完成任务人数
     *
     * @return
     */
    public Integer getTaskNumber();

}
