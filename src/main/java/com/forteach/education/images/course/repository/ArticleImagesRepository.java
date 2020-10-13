package com.forteach.education.images.course.repository;

import com.forteach.education.images.course.domain.ArticleImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资讯内容图片信息
 *
 * @Auther: zjw
 * @Email:
 * @Date: 18-11-29 14:58
 * @Version: 1.0
 * @Description:
 */
public interface ArticleImagesRepository extends JpaRepository<ArticleImages, String> {
    /**
     * 根据有效状态和科目课程查询对应的轮播图信息 排序方式正序
     *
     * @param articleId
     * @return
     */
    @Transactional(readOnly = true)
    List<ArticleImages> findByArticleIdOrderByIndexNumAsc(String articleId);
}
