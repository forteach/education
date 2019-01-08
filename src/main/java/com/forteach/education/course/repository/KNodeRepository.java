package com.forteach.education.course.repository;

import com.forteach.education.course.domain.KNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    //根据章节编号查找知识点
    List<KNode> findByChapterIdAndKNodeTypeIsGreaterThanAndIsValidated(String chapterId,int kNodeType,String isVal);

    //根据课程编号查找知识点
    List<KNode> findByCourseIdAndIsValidated(String courseId,String isVal);

//    Page<KNode> findAll(Specification<KNode> specification, Pageable pageable);

}
