package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.LinkDatum;
import com.forteach.education.databank.dto.ChapterDataNumDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:21
 * @Version: 1.0
 * @Description: 链接资料库
 */
public interface LinkDatumRepository extends IDatumRepoitory<LinkDatum, String> {
    /**
     * 修改资料领域
     *
     * @param fileId
     * @param datumArea
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE LinkDatum p SET p.datumArea = :datumArea where p.fileId = :fileId")
    public void updateDatumArea(String fileId, String datumArea);

    /**
     * 修改教师分享
     *
     * @param fileId
     * @param teachShare
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE LinkDatum p SET p.teachShare = :teachShare where p.fileId = :fileId")
    public void updateTeachShare(String fileId, String teachShare);

    /**
     * 修改学生可见
     *
     * @param fileId
     * @param stuShare
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE LinkDatum p SET p.stuShare = :stuShare where p.fileId = :fileId")
    public void updateStuShare(String fileId, String stuShare);

    /**
     * 按照课程统计分组链接资料信息
     *
     * @return
     */
    @Query(value = "select courseId, chapterId, count(fileId) as dataNum from LinkDatum where isValidated = '0' group by courseId")
    List<ChapterDataNumDto> findAllByIsValidatedGroupByCourseId();
}
