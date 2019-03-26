package com.forteach.education.common.keyword;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:07
 */
public class Dic {
    //isValidated 的默认值
    public static final String TAKE_EFFECT_OPEN = "0";

    public static final String TAKE_EFFECT_CLOSE = "1";

    /**
     * 私有
     */
    public static final int COURSE_SHARE_TYPE_PRIVATE = 1;
    /**
     * 协作
     */
    public static final int COURSE_SHARE_TYPE_COOPERATION = 2;
    /**
     * 公开
     */
    public static final int COURSE_SHARE_TYPE_PUBLIC = 3;

    /**
     * 课程章节发布
     */
    public static final String PUBLISH_YES = "Y";
    /**
     * 课程章节不发布
     */
    public static final String PUBLISH_NO = "N";
    /**
     * 章节科目父节点ID
     */
    public static final String COURSE_CHAPTER_CHAPTER_PARENT_ID = "0";
    /**
     * 是当前树结构第一个
     */
    public static final String COURSE_CHAPTER_SORT = "1";
    /**
     * 所处的层级是2
     */
    public static final String COURSE_CHAPTER_LEVERL = "2";

    /**
     * 个人备课
     */
    public static final String LESSON_PREPARATION_TYPE_PRIVATE = "1";
    /**
     * 集体备课
     */
    public static final String LESSON_PREPARATION_TYPE_GROUP = "2";

    /**
     * 课程分享范围-全部
     */
    public static final String COURSE_SHARE_AREA_ALL = "1";

    /**
     * 课程分享范围-章节
     */
    public static final String COURSE_SHARE_AREA_CHAPTER = "2";

    /**
     * 1文档　2图册　3视频　4音频　5链接
     */
    public static final String COURSE_ZILIAO_FILE = "1";
    public static final String COURSE_ZILIAO_PHOTO = "2";
    public static final String COURSE_ZILIAO_VIEW = "3";
    public static final String COURSE_ZILIAO_AUDIO = "4";
    public static final String COURSE_ZILIAO_LINK = "5";

    /**
     * 用户 token 前缀
     */
    public final static String USER_TOKEN_PREFIX ="userToken$";
    /**
     * 用户token 的有效时间
     */
    public final static Long TOKEN_VALIDITY_TIME = 3600L * 24 * 7;

    /**
     * 微信端学生类型
     */
    public final static String TOKEN_STUDENT ="student";
    /**
     * 登录认证的教师类型
     */
    public final static String TOKEN_TEACHER ="teacher";
}
