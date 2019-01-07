package com.forteach.education.databank.repository.ziliao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IDatumRepoitory<T,ID>  extends JpaRepository<T, ID> {

    Page<T> findByIsValidated(String isValidated, Pageable pageable);

    /**
     * 查询科目有效资料信息
     * @param isValidated
     * @param courseId
     * @return
     */
    public List<T> findByCourseIdAndIsValidated(String courseId, String isValidated);
    /**
     * 根据有效状态可科目ID查询章节资料信息
     * @param isValidated
     * @param chapterId
     * @return
     */
    public  List<T> findByChapterIdAndIsValidated(String chapterId,String isValidated);

    /**
     * 分页查询科目课程文件信息
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @return
     */
    public Page<T> findByCourseIdAndChapterIdAndIsValidated( String courseId, String chapterId,String isValidated, Pageable pageable);


    /**
     * 分页查询科目章节文件类型文件信息
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @return
     */
    public Page<T> findByCourseIdAndChapterIdAndDatumTypeAndIsValidated( String courseId, String chapterId,String datumType,String isValidated, Pageable pageable);
    /**
     * 多条件查询课程科目文件挂载
     * @param specification
     * @param pageable
     * @return
     */
    public Page<T> findAll(Specification<T> specification, Pageable pageable);

}
