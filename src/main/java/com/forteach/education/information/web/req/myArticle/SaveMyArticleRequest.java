package com.forteach.education.information.web.req.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保存收藏
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveMyArticleRequest implements Serializable {

    private String articleId;

    /**
     * 发布者.
     */
    private String userId;

    /**
     * 我的文章标签类型 0：我发布的 1：我收藏的
     * //发布者编号
     */
    private String tagType;
}