package com.forteach.education.repository;

import com.forteach.education.domain.ViewDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:16
 * @Version: 1.0
 * @Description: 视频资料库
 */
public interface ViewDatumRepository extends JpaRepository<ViewDatum, String> {

    /**
     * 分页查询生效视频资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<ViewDatum> findByIsValidatedEquals(String isValidated, Pageable pageable);
}
