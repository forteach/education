package com.forteach.education.classes.repository;

import com.forteach.education.classes.domain.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:42
 * @Version: 1.0
 * @Description: 班级分组
 */
public interface ClassGroupRepository extends JpaRepository<ClassGroup, String> {
}
