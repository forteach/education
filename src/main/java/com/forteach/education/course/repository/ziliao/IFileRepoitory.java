package com.forteach.education.course.repository.ziliao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoRepositoryBean
public interface IFileRepoitory<T, ID> extends JpaRepository<T, ID> {

    @Transactional(readOnly = true)
    Page<T> findByIsValidated(String isValidated, Pageable pageable);

    /**
     * 根据有效状态可科目ID查询章节资料信息
     *
     * @param isValidated
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findByChapterIdAndIsValidated(String chapterId, String isValidated);

    /**
     * 分页查询科目课程文件信息
     *
     * @param isValidated
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> findByChapterIdAndIsValidated(String chapterId, String isValidated, Pageable pageable);


    /**
     * 分页查询科目章节文件类型文件信息
     *
     * @param isValidated
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> findByChapterIdAndDatumTypeAndIsValidated(String chapterId, String datumType, String isValidated, Pageable pageable);

    /**
     * 分页查询科目章节文件类型文件信息
     *
     * @param isValidated
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findByChapterIdAndDatumTypeAndIsValidated(String chapterId, String datumType, String isValidated);

    /**
     * 根据章节、文件类型 按时间倒序排列
     *
     * @param chapterId
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> findByChapterIdAndIsValidatedOrderByCreateTimeAsc(String chapterId, String isValidated, Pageable pageable);


    /**
     * 根据章节、文件类型 按时间倒序排列
     *
     * @param chapterId
     * @param datumType
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> findByChapterIdAndDatumTypeAndIsValidatedOrderByCreateTimeAsc(String chapterId, String datumType, String isValidated, Pageable pageable);

    /**
     * 根据章节、文件类型、知识点 按时间倒序排列
     *
     * @param chapterId
     * @param datumType
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> findByChapterIdAndDatumTypeAndKNodeIdAndIsValidatedOrderByCreateTimeAsc(String chapterId, String datumType, String kNodeId, String isValidated, Pageable pageable);


    /**
     * 多条件查询课程科目文件挂载
     *
     * @param specification
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<T> findAll(Specification<T> specification, Pageable pageable);


}
