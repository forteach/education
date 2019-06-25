package com.forteach.education.images.course.repository;

import com.forteach.education.images.course.domain.CourseImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

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
     *
     * @param isValidated
     * @param courseId
     * @return
     */
    @Transactional(readOnly = true)
    List<CourseImages> findByIsValidatedEqualsAndCourseIdOrderByIndexNumAsc(String isValidated, String courseId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    int deleteAllByCourseId(String courseId);
}
