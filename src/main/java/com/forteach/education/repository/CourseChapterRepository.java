package com.forteach.education.repository;

import com.forteach.education.domain.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:41
 * @Version: 1.0
 * @Description:　科目章节
 */
public interface CourseChapterRepository extends JpaRepository<CourseChapter, String> {

    /**
     * 根据查询科目编号查询父章节编号是空的章节并按照正序排列
     * 查询条件 1、有效 2 父章节编号是空 3 按照层级正序排列
     */
    List<CourseChapter> findByCourseIdAndIsValidatedEqualsAndChAndChapterParentIdIsNullOrderBySortAsc(String courseId);
}
