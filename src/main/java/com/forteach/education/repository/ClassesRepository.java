package com.forteach.education.repository;

import com.forteach.education.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:31
 * @Version: 1.0
 * @Description: 班级
 */
public interface ClassesRepository extends JpaRepository<Classes, String> {
}
