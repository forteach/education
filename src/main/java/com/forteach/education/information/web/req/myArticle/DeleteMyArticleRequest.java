package com.forteach.education.information.web.req.myArticle;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 取消收藏
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMyArticleRequest implements Serializable {
private String userId;
private String articleId;

}
