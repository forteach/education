package com.forteach.education.repository;

import com.forteach.education.domain.FileDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
