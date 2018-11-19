package com.forteach.education.repository;

import com.forteach.education.domain.ArticleTagMap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:56
 * @Version: 1.0
 * @Description: 文章标签对应表
 */
public interface ArticleTagMapRepository extends JpaRepository<ArticleTagMap, String> {
}
