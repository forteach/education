package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:37
 * @Version: 1.0
 * @Description: 分享范围 1、 全部 2、 章节
 */
public interface CourseShareRepository extends JpaRepository<CourseShare, String> {

    @Transactional(readOnly = true)
    public List<CourseShare> findByCourseId(String courseId);

    @Transactional(readOnly = true)
    public CourseShare findByCourseIdAndShareArea(String courseId, String shareArea);

}
