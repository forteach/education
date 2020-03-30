package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseChapterReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 11:08
 * @version: 1.0
 * @description:
 */
public interface CourseChapterReviewRepository extends JpaRepository<CourseChapterReview, String> {

    /**
     * 查询根据章节id查询
     * @param isValidated
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    Optional<CourseChapterReview> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);
}
