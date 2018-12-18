package com.forteach.education.information.repository;


import com.forteach.education.information.domain.ArticleSort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:38
 * @Version: 1.0
 * @Description:　文章分类
 */
public interface ArticleSortRepository extends JpaRepository<ArticleSort, String> {

}
