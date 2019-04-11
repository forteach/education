package com.forteach.education.classes.repository;

import com.forteach.education.classes.domain.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:31
 * @Version: 1.0
 * @Description: 班级
 */
public interface ClassesRepository extends JpaRepository<Classes, String> {

    /**
     * 查询所用班级信息倒叙排列
     * @param isValidated
     * @return
     */
    @Transactional(readOnly = true)
    List<Classes> findByIsValidatedEqualsOrderByCreateTimeDesc(String isValidated);

    /**
     * 分页查询班级信息
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    Page<Classes> findByIsValidatedEqualsOrderByCreateTimeDesc(String isValidated, Pageable pageable);
}
