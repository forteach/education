package com.forteach.education.repository;

import com.forteach.education.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<Teacher> findByIsValidatedEquals(String isValidated, Pageable pageable);


    /**
     * 根据专业ID查询教师信息列表
     * @param isValidated
     * @param specialtyId
     * @return
     */
    List<Teacher> findByIsValidatedEqualsAndSpecialtyId(String isValidated, String specialtyId);
}
