package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.dto.CourseShareTeacherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:37
 * @Version: 1.0
 * @Description: 分享范围 1、 全部 2、 章节
 */
public interface CourseShareRepository extends JpaRepository<CourseShare, String> {

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    int deleteByCourseId(String courseId);

    List<CourseShare> findByCourseId(String courseId);

//    /**
//     * 根据课程ID查询对应的协作者教师ID
//     * @param courseId
//     * @return
//     */
//    @Query(value = "select new CourseShareTeacherDto " +
//            "(t.teacherId, t.teacherName) from CourseShare AS c left join Teacher AS t on c.teacherId = t.teacherId where " +
//            "c.isValidated = '0' AND c.courseId = :courseId")
//    List<CourseShareTeacherDto> findTeachersByCourseId(@Param("courseId") String courseId);
}
