package com.forteach.education.information.web.req.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindArticleByKeyWordsPostNameRequest implements Serializable {
private String userId;
private String keywords;
private String catName;
private int  pageNo;


}
