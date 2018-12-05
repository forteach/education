package com.forteach.education.repository;

import com.forteach.education.domain.KNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:03
 * @Version: 1.0
 * @Description:
 */
public interface KNodeRepository extends JpaRepository<KNode, String> {
    /**
     * 通过有效和课程科目ID查询知识点列表信息
     * @param isValidated
     * @param courseId
     * @return
     */
    List<KNode> findByIsValidatedEqualsAndCourseId(String isValidated, String courseId);

    /**
     * 通过章节查询 知识点列表
     * @param isValidated
     * @param chapterId
     * @return
     */
    List<KNode> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);

    /**
     * 通过资料ID 查询对应的知识点
     * @param isValidated
     * @param dataId
     * @return
     */
    List<KNode> findByIsValidatedEqualsAndDataId(String isValidated, String dataId);
}
