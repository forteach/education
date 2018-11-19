package com.forteach.education.repository;

import com.forteach.education.domain.AdTagMap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 11:01
 * @Version: 1.0
 * @Description: 广告标签对应表
 */
public interface AdTagMapRepository extends JpaRepository<AdTagMap, String> {
}
