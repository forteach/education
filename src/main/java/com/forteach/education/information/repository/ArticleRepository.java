package com.forteach.education.information.repository;


import com.forteach.education.information.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:38
 * @Version: 1.0
 * @Description: 文章表数据
 */
public interface ArticleRepository extends JpaRepository<Article, String> {

}
