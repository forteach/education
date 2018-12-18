package com.forteach.education.information.repository;


import com.forteach.education.information.domain.AdTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 11:02
 * @Version: 1.0
 * @Description: 广告标签
 */
public interface AdTagRepository extends JpaRepository<AdTag, String> {
}
