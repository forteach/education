package com.forteach.education.information.web.res.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 收藏列表
 *
 * @author admin
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCollectArticleResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 头像地址
     */
    private String imgUrl;
    /**
     * 名称
     */
    private String alias;
    /**
     * 标题
     */
    private String title;
    /**
     * 点击量
     */
    private int clickCount;
    /**
     * 收藏量
     */
    private int collectCount;
    /**
     * 添加时间
     */
    private String addTime;
}