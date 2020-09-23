package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.AudioDatum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:52
 * @Version: 1.0
 * @Description: 音频资料库
 */
public interface AudioDatumRepository extends IDatumRepoitory<AudioDatum, String> {

    /**
     * 修改资料领域
     *
     * @param fileId
     * @param datumArea
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE AudioDatum p SET p.datumArea = :datumArea where p.fileId = :fileId")
    public void updateDatumArea(String fileId, String datumArea);

    /**
     * 修改教师分享
     *
     * @param fileId
     * @param teachShare
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE AudioDatum p SET p.teachShare = :teachShare where p.fileId = :fileId")
    public void updateTeachShare(String fileId, String teachShare);

    /**
     * 修改学生可见
     *
     * @param fileId
     * @param stuShare
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE AudioDatum p SET p.stuShare = :stuShare where p.fileId = :fileId")
    public void updateStuShare(String fileId, String stuShare);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE AudioDatum set isValidated = :isValidated WHERE courseId = :courseId and chapterId = :chapterId")
    public int updateIsValidated(String isValidated, String courseId, String chapterId);
}