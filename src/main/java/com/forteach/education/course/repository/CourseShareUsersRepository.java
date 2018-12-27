package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseShareUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:37
 * @Version: 1.0
 * @Description: 协作成员列表
 */
public interface CourseShareUsersRepository extends JpaRepository<CourseShareUsers, String> {



    @Modifying
    @Transactional(rollbackOn = Exception.class)
    public int deleteByShareId(String shareId);

    public List<CourseShareUsers> findByShareId(String shareId);
//
//    List<CourseShare> findByCourseId(String courseId);
//
//    /**
//     * 根据课程ID查询对应的协作者教师ID
//     * @param courseId
//     * @return
//     */
//    @Query(value = "select  " +
//            "teacherId as teacherId, teacherName as teacherName from CourseShare where " +
//            "isValidated = '0' AND courseId = :courseId")
//    List<ICourseShareTeacherDto> findTeachersByCourseId(@Param("courseId") String courseId);
//
//    @Query("select courseId as courseId  from CourseShare ")
//    List<ICourseShareTeacherDto1> findCourseList();

}
