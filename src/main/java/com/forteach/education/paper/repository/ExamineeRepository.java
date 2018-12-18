package com.forteach.education.paper.repository;

import com.forteach.education.paper.domain.Examinee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:56
 * @Version: 1.0
 * @Description:　考生信息
 */
public interface ExamineeRepository extends JpaRepository<Examinee, String> {
}
