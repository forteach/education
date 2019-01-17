package com.forteach.education.course.repository;

import com.forteach.education.course.domain.Course;
import com.forteach.education.course.dto.ICourseListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:29
 * @Version: 1.0
 * @Description:　科目
 */
public interface CourseRepository extends JpaRepository<Course, String> {

    /**
     * 分页查询生效视频资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<ICourseListDto> findByIsValidatedEquals(String isValidated, Pageable pageable);

    /**
     * 分页查询我的课程科目
     * @param isValidated
     * @param cUser
     * @param pageable
     * @return
     */
    Page<Course> findByIsValidatedEqualsAndCreateUser(String isValidated, String cUser, Pageable pageable);
}
