package com.forteach.education.course.repository;

import com.forteach.education.course.domain.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:52
 * @Version: 1.0
 * @Description: 专业
 */
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {

    /**
     * 分页查询生效科目信息
     *
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    Page<Specialty> findByIsValidatedEqualsOrderByCreateTimeDesc(String isValidated, Pageable pageable);

    /**
     * 根据名字和有效状态查询课程信息
     * @param isValidated
     * @param specialtyName
     * @return
     */
    @Transactional(readOnly = true)
    Optional<Specialty> findByIsValidatedEqualsAndSpecialtyName(String isValidated, String specialtyName);
}
