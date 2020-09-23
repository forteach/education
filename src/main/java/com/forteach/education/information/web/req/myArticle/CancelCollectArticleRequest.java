package com.forteach.education.information.web.req.myArticle;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取消收藏
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelCollectArticleRequest {

    private String articleId;

    private String userId;

    private String postUserId;

}