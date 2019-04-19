package com.forteach.education.information.web.res.myArticle;

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
public class DeleteMyArticleResponse implements Serializable {

private String flag;

}
