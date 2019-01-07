package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.ViewDatum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:16
 * @Version: 1.0
 * @Description: 视频资料库
 */
public interface ViewDatumRepository extends IDatumRepoitory<ViewDatum, String> {

    /**
     * 分页查询生效视频资源
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<ViewDatum> findByIsValidatedEquals(String isValidated, Pageable pageable);

    /**
     * 根据对应的章节查询有效视频资料信息
     * @param chapterId
     * @return
     */
    List<ViewDatum> findByIsValidatedEqualsAndChapterId(String isValidated, String chapterId);

    /**
     * 查询课程视频信息
     * 条件　１，是否有效　２　课程ID 3 课程id为空是课程id信息
     * @param courseId
     * @return
     */
    List<ViewDatum> findByIsValidatedEqualsAndCourseIdAndChapterIdIsNull(String isValidated, String courseId);
}
