package com.forteach.education.repository;

import com.forteach.education.domain.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<Specialty> findByIsValidatedEquals(String isValidated, Pageable pageable);

    Specialty findByIsValidatedEqualsAndSpecialtyName(String isValidated,String specialtyName);
}
