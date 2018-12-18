package com.forteach.education.information.repository;

import com.forteach.education.information.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 11:03
 * @Version: 1.0
 * @Description: 广告
 */
public interface AdRepository extends JpaRepository<Ad, String> {
}
