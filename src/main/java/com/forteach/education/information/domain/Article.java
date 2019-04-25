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
    public static String STATUS_TEXT = "txt";

    /** 链接类型. */
    public static String STATUS_LINK = "link";

    /** 图片类型. */
    public static String STATUS_IMAGE = "img";

    /** 文章编号.**/
    @Id
    @Column(length = 40, nullable = false, name = "article_id")
    private String articleId;

    /** 课程编号.**/
    @Column(length = 40, nullable = false, name = "course_Id")
    private String courseId;

    /** 课程编号.**/
    @Column(length = 40,  name = "course_name")
    private String courseName;

    /** 发布人编号.**/
    @Column(length = 40, nullable = false, name = "user_id")
    private String userId;

    /** 发布人名称.**/
    @Column(length = 40,  name = "user_name")
    private String userName;

    /** 发布人头像.**/
    @Column(length = 255,  name = "user_tortrait")
    private String userTortrait;

    /** 班级编号.**/
    @Column(length = 40, nullable = false, name = "class_id")
    private String classId;

    /** 班级名称.**/
    @Column(length = 40,  name = "class_name")
    private String className;

    /** 文章题目.**/
    @Column(length = 150)
    private String title;

    // 标题图片连接.**/
    @Column(length = 255, name = "img_url")
    private String imgUrl;

    // 外资料引用连接.**/
    @Column(length = 255, name = "link_url")
    private String linkUrl;

    //@Transient
    /** 是否置顶.**/
    @Column(name = "is_top")
    private String isTop;

    //@Transient
    /** 是否精华.**/
    @Column(name = "is_nice")
    private String isNice;

    /** 文章描述.**/
    @Column(length = 255)
    private String description;

    /** 文章内容.**/
    @Column(name = "article_conten", columnDefinition = "text(65535) COMMENT '文章内容'")
    private String articleConten;

    /** 点击量.**/
    @Column(name = "click_count")
    private int clickCount;

    /** 收藏数量 **/
    @Column(name = "collect_count")
    private int collectCount;

    /** 点赞数量 **/
    @Column(name = "click_good")
    private int clickGood;

    /** 评论数量 **/
    @Column(name = "comment_count")
    private int commentCount;

    /** 资讯分类. **/

    @Column(length = 10,name = "article_type")
    private String articleType;

//    /** 资讯详情图片集合. **/
//    @Transient
//    @Formula("(select t.alias from user_member t where t.user_id = user_id)")
//    private List artImages;

}
