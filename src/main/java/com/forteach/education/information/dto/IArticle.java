package com.forteach.education.information.dto;

public interface IArticle {

    /** 文章编号.**/
    public String getArticleId();

    /** 课程编号.**/
    public String getCourseId();

    /** 文章题目.**/
    public String getTitle();

    // 图片连接.**/
    public String getImgUrl();

    /** 点击量.**/
    public int getClickCount();

    /** 收藏数量 **/
    public int getCollectCount();

    /** 点赞数量 **/
    public int getClickGood();

    /** 资讯分类. **/
    public String getArticleType();

    /** 发布人编号.**/
    public String getUserId();

    /** 班级编号.**/
    public String getClassId();

    /** 判断是否置顶.**/
    public String getIsTop();

    /** 创建时间.**/
    public String getCreateTime();
}
