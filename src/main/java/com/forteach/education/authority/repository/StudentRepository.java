package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.course.dto.IStudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-2-14 10:59
 * @version: 1.0
 * @description:
 */
public interface StudentRepository extends JpaRepository<StudentEntitys, String> {

    /**
     * 查询有效的班级学生信息
     * @param classId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Query(value = "select id as studentId, userName as studentName, portrait as portrait, classId as classId from StudentEntitys where isValidated = '0' and classId = ?1 order by createTime asc ")
    List<IStudentDto> findByIsValidatedEqualsAndClassIdOrderByCreateTime(String classId);

}
