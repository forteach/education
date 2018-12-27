package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.dto.ICourseShareTeacherDto;
import com.forteach.education.course.dto.ICourseShareTeacherDto1;
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

//    @Modifying
//    int deleteByCourseId(String courseId);

    public List<CourseShare> findByCourseId(String courseId);

//    /**
//     * 根据课程ID查询对应的协作者教师ID
//     * @param courseId
//     * @return
//     */
//    @Query(value = "select  " +
//            "teacherId as teacherId, teacherName as teacherName from CourseShare where " +
//            "isValidated = '0' AND courseId = :courseId")
//    List<ICourseShareTeacherDto> findTeachersByCourseId(@Param("courseId") String courseId);


   public  CourseShare findByAndCourseIdAndAndShareArea(String courseId,String shareArea );

}
