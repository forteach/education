package com.forteach.education.information.web.req.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindArticleByUserIdArticleIdRequest  {
private String userId;//用户id
private String articleId;

}
