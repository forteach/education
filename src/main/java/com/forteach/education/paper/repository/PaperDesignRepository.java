package com.forteach.education.paper.repository;


import com.forteach.education.paper.domain.PaperDesign;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:23
 * @Version: 1.0
 * @Description:　试卷简答思考题
 */
public interface PaperDesignRepository extends JpaRepository<PaperDesign, String> {

}
