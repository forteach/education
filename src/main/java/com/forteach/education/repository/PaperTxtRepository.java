package com.forteach.education.repository;

import com.forteach.education.domain.PaperTxt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:04
 * @Version: 1.0
 * @Description: 试卷内容
 */
public interface PaperTxtRepository extends JpaRepository<PaperTxt, String> {

}
