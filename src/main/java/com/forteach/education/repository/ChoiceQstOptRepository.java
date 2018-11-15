package com.forteach.education.repository;

import com.forteach.education.domain.ChoiceQstOpt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:32
 * @Version: 1.0
 * @Description:　选择题选项
 */
public interface ChoiceQstOptRepository extends JpaRepository<ChoiceQstOpt, String> {
    
}
