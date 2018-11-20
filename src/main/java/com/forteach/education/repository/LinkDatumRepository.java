package com.forteach.education.repository;

import com.forteach.education.domain.LinkDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
