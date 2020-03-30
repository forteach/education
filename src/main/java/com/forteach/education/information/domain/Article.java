package com.forteach.education.information.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;



/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 16:22
 * @Version: 1.0
 * @Description: 文章
 */
@Data
@Entity
@Table(name = "article")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "article", comment = "文章")
@AllArgsConstructor
@NoArgsConstructor
public class Article extends Entitys implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /** 文章类型. */
    @Transient
    public static String STATUS_TEXT = "txt";

    /** 链接类型. */
    @Transient
    public static String STATUS_LINK = "link";

    /** 图片类型. */
    @Transient
    public static String STATUS_IMAGE = "img";

    /** 文章编号.**/
    @Id
    @Column(name = "article_id", columnDefinition = "VARCHAR(40) COMMENT '文章编号'")
    private String articleId;

    /** 课程编号.**/
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程编号'")
    private String courseId;

    /** 课程名称.**/
    @Column(name = "course_name", columnDefinition = "VARCHAR(32) COMMENT '课程名称'")
    private String courseName;

    /** 发布人编号.**/
    @Column(name = "user_id", columnDefinition = "VARCHAR(32) COMMENT '发布人id'")
    private String userId;

    /** 发布人名称.**/
    @Column(name = "user_name", columnDefinition = "VARCHAR(32) COMMENT '发布人名称'")
    private String userName;

    /** 发布人头像.**/
    @Column(name = "user_tortrait", columnDefinition = "VARCHAR(255) COMMENT '发布人头像'")
    private String userTortrait;

    /** 班级编号.**/
    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级id'")
    private String classId;

    /** 班级名称.**/
    @Column(name = "class_name", columnDefinition = "VARCHAR(32) COMMENT '班级名称'")
    private String className;

    /** 文章题目.**/
    @Column(name = "title", columnDefinition = "VARCHAR(256) COMMENT '文章题目'")
    private String title;

    /** 标题图片连接.**/
//    @Column(name = "img_url", columnDefinition = "VARCHAR(256) COMMENT '标题图片地址链接'")
//    private String imgUrl;

    /** 外资料引用连接.**/
    @Column(name = "link_url", columnDefinition = "VARCHAR(256) COMMENT '外资料引用链接'")
    private String linkUrl;

    //@Transient
    /** 是否置顶.**/
    @Column(name = "is_top", columnDefinition = "VARCHAR(32) COMMENT '是否置顶'")
    private String isTop;

    //@Transient
    /** 是否精华.**/
    @Column(name = "is_nice", columnDefinition = "VARCHAR(32) COMMENT '是否精华'")
    private String isNice;

    /** 文章描述.**/
    @Column(name = "description", columnDefinition = "VARCHAR(2000) COMMENT '文章描述'")
    private String description;

    /** 文章内容.**/
    @Column(name = "article_conten", columnDefinition = "text(65535) COMMENT '文章内容'")
    private String articleConten;

    /** 点击量.**/
    @Column(name = "click_count", columnDefinition = "VARCHAR(32) COMMENT '点击量'")
    private int clickCount;

    /** 收藏数量 **/
    @Column(name = "collect_count", columnDefinition = "VARCHAR(32) COMMENT '收藏数量'")
    private int collectCount;

    /** 点赞数量 **/
    @Column(name = "click_good", columnDefinition = "VARCHAR(32) COMMENT '点击数量'")
    private int clickGood;

    /** 评论数量 **/
    @Column(name = "comment_count", columnDefinition = "VARCHAR(32) COMMENT '评论击量'")
    private int commentCount;

    /** 资讯分类. **/
    @Column(name = "article_type", columnDefinition = "VARCHAR(32) COMMENT '咨询分类'")
    private String articleType;

//    /** 资讯详情图片集合. **/
//    @Transient
//    @Formula("(select t.alias from user_member t where t.user_id = user_id)")
//    private List artImages;

}
