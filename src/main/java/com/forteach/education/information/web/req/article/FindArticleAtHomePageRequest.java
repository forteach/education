package com.forteach.education.information.web.req.article;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zjw
 * @ClassName: FindArticleAtHomePageRequest
 * @Description: TODO(在首页上查看资讯信息 ， 显示推荐的6条资讯)
 * @date
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindArticleAtHomePageRequest implements Serializable {
    private String modId;

}
