package com.forteach.education.repository;

import com.forteach.education.domain.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:41
 * @Version: 1.0
 * @Description:　科目章节
 */
public interface CourseChapterRepository extends JpaRepository<CourseChapter, String> {
}
