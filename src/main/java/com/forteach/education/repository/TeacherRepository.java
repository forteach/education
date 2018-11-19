package com.forteach.education.repository;

import com.forteach.education.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:41
 * @Version: 1.0
 * @Description: 教师
 */
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    /**
     * 分页查询生效用户
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<Teacher> findByIsValidatedEquals(String isValidated, Pageable pageable);
}