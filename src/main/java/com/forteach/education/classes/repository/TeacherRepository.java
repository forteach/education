package com.forteach.education.classes.repository;

import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.dto.ITeacherInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:41
 * @Version: 1.0
 * @Description: 教师表操作
 */
public interface TeacherRepository extends JpaRepository<Teacher, String>, JpaSpecificationExecutor<Teacher> {

    /**
     * 分页查询有效的教师信息
     *
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    Page<Teacher> findByIsValidatedEqualsOrderByCreateTimeDesc(String isValidated, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Teacher> findAllByIsValidatedEqualsAndTeacherNameContaining(String isValidated, String teacherName, Pageable pageable);

    /**
     * 查询有效教师信息
     *
     * @param isValidated
     * @return
     */
    @Transactional(readOnly = true)
    @Query(value = "select teacherId AS teacherId, teacherCode AS teacherCode, teacherName AS teacherName FROM Teacher WHERE isValidated = :isValidated ORDER BY teacherName ASC ")
    List<ITeacherInfoDto> findByIsValidatedEquals(String isValidated);


    /**
     * 根据专业ID查询教师信息列表
     *
     * @param isValidated
     * @param specialtyId
     * @return
     */
    @Transactional(readOnly = true)
    List<Teacher> findByIsValidatedEqualsAndSpecialtyId(String isValidated, String specialtyId);

    /**
     * 查询修改的密码账户信息
     *
     * @param isValidated
     * @param uTime
     * @return
     */
    @Transactional(readOnly = true)
    List<Teacher> findByIsValidatedEqualsAndUpdateTime(String isValidated, String uTime);
}
