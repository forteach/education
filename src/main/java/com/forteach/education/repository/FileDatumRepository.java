package com.forteach.education.repository;

import com.forteach.education.domain.FileDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:33
 * @Version: 1.0
 * @Description:　文档资料库
 */
public interface FileDatumRepository extends JpaRepository<FileDatum, String> {
    /**
     * 分页查询生效文件资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<FileDatum> findByIsValidatedEquals(String isValidated, Pageable pageable);

    /**
     * 根据对应的章节查询有效文件资料信息
     * @param chapterId
     * @return
     */
    List<FileDatum> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);

    /**
     * 根据科目课程查询对应的文件信息
     * @param isValidated
     * @param courseId
     * @return
     */
    List<FileDatum> findByIsValidatedAndCourseId(String isValidated, String courseId);

    /**
     * 分页查询科目课程信息
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @return
     */
    Page<FileDatum> findByIsValidatedAndCourseIdAndChapterId(String isValidated, String courseId, String chapterId, Pageable pageable);
}
