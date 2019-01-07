package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.AudioDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:52
 * @Version: 1.0
 * @Description: 音频资料库
 */
public interface AudioDatumRepository extends IDatumRepoitory<AudioDatum, String> {
    /**
     * 分页查询生效音频资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<AudioDatum> findByIsValidatedEquals(String isValidated, Pageable pageable);

    /**
     * 根据对应的章节查询有效音频资料信息
     * @param chapterId
     * @return
     */
    List<AudioDatum> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);
}