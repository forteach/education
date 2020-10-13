package com.forteach.education.information.dto;

public interface IArticle {

    /**
     * 文章编号.
     **/
    public String getArticleId();

    /**
     * 课程编号.
     **/
    public String getCourseId();

    public String getCourseName();

    /**
     * 文章题目.
     **/
    public String getTitle();

    /**
     * 文章简介.
     **/
    public String getDescription();

    /** 图片连接.**/
//    public String getImgUrl();

    /**
     * 点击量.
     **/
    public int getClickCount();

    /**
     * 收藏数量
     **/
    public int getCollectCount();

    /**
     * 点赞数量
     **/
    public int getClickGood();

    /**
     * 资讯分类.
     **/
    public String getArticleType();

    /**
     * 发布人编号.
     **/
    public String getUserId();

    public String getUserName();

    public String getUserTortrait();

    /**
     * 班级编号.
     **/
    public String getClassId();

    public String getClassName();

    /**
     * 判断是否精华.
     **/
    public String getIsNice();

    /**
     * 创建时间.
     **/
    public String getCreateTime();
}
