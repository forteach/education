package com.forteach.education.repository;

import com.forteach.education.domain.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:52
 * @Version: 1.0
 * @Description: 专业
 */
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {
}
