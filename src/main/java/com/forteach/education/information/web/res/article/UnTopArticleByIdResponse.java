package com.forteach.education.information.web.res.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnTopArticleByIdResponse implements Serializable {

    private String flag;

}