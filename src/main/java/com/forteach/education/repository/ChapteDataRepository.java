package com.forteach.education.repository;

import com.forteach.education.domain.ChapteData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:44
 * @Version: 1.0
 * @Description: 章节资料
 */
public interface ChapteDataRepository extends JpaRepository<ChapteData, String> {
    /**
     * 查询科目有效资料信息
     * @param isValidated
     * @param courseId
     * @return
     */
    List<ChapteData> findByIsValidatedEqualsAndCourseId(String isValidated, String courseId);
    /**
     * 根据有效状态可科目ID查询章节资料信息
     * @param isValidated
     * @param chapterId
     * @return
     */
    List<ChapteData> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);
}
