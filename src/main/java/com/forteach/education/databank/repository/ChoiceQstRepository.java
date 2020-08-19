package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.ChoiceQst;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:36
 * @Version: 1.0
 * @Description: 选择题
 */
public interface ChoiceQstRepository extends JpaRepository<ChoiceQst, String> {

}
