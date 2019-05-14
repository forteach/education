package com.forteach.education.count.repository;

import com.forteach.education.count.domain.CourseJoinChapter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-14 10:26
 * @version: 1.0
 * @description:
 */
public interface CourseJoinChapterRepository extends JpaRepository<CourseJoinChapter, String> {
    /**
     * 查询有效的课堂签到信息
     * @param isValidated
     * @param circleId
     * @return
     */
    CourseJoinChapter findByIsValidatedEqualsAndCircleId(String isValidated, String circleId);
}
