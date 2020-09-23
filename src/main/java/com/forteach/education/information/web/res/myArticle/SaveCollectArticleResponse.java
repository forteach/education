package com.forteach.education.information.web.res.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 收藏文章保存
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveCollectArticleResponse implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    private String flag;

}
