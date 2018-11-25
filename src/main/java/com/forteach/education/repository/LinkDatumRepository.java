package com.forteach.education.repository;

import com.forteach.education.domain.LinkDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:21
 * @Version: 1.0
 * @Description: 链接资料库
 */
public interface LinkDatumRepository extends JpaRepository<LinkDatum, String> {
    /**
     * 分页查询链接资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<LinkDatum> findByIsValidatedEquals(String isValidated, Pageable pageable);

    /**
     * 根据对应的章节查询有效链接资料信息
     * @param chapterId
     * @return
     */
    List<LinkDatum> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);
}
