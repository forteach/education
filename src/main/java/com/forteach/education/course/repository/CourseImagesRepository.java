package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 14:58
 * @Version: 1.0
 * @Description:
 */
public interface CourseImagesRepository extends JpaRepository<CourseImages, String> {
    /**
     * 根据有效状态和科目课程查询对应的轮播图信息 排序方式正序
     * @param isValidated
     * @param courseId
     * @return
     */
    List<CourseImages> findByIsValidatedEqualsAndCourseIdOrderByIndexNumAsc(String isValidated, String courseId);
}
