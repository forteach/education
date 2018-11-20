package com.forteach.education.repository;

import com.forteach.education.domain.AudioDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:52
 * @Version: 1.0
 * @Description: 音频资料库
 */
public interface AudioDatumRepository extends JpaRepository<AudioDatum, String> {
    /**
     * 分页查询生效音频资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<AudioDatum> findByIsValidatedEquals(String isValidated, Pageable pageable);
}
