package com.forteach.education.repository;

import com.forteach.education.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:00
 * @Version: 1.0
 * @Description:　简答思考题
 */
public interface DesignRepository extends JpaRepository<Design, String> {
}
