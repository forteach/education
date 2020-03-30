package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.TrueOrFalse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:47
 * @Version: 1.0
 * @Description:　判断题
 */
public interface TrueOrFalseRepository extends JpaRepository<TrueOrFalse, String> {

}
