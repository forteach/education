package com.forteach.education.course.repository.ziliao;

import com.forteach.education.course.domain.ziliao.CourseData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:44
 * @Version: 1.0
 * @Description: 章节资料
 */
public interface CourseDataRepository extends IFileRepoitory<CourseData, String> {
    /**
     * 修改资料领域
     *
     * @param dataId
     * @param datumArea
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CourseData p SET p.datumArea = :datumArea where p.dataId = :dataId")
    public void updateDatumArea(String dataId, String datumArea);

    /**
     * 修改教师分享
     *
     * @param dataId
     * @param teachShare
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CourseData p SET p.teachShare = :teachShare where p.dataId = :dataId")
    public void updateTeachShare(String dataId, String teachShare);

    /**
     * 修改学生可见
     *
     * @param dataId
     * @param stuShare
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CourseData p SET p.stuShare = :stuShare where p.dataId = :dataId")
    public void updateStuShare(String dataId, String stuShare);

    @Transactional
    @Modifying(clearAutomatically = true)
    public int deleteByChapterId(String chapterId);

    /**
     * 删除无效挂载文件
     *
     * @param chapterId
     * @param dataIds
     * @return
     */
    public int deleteByChapterIdAndDataIdIn(String chapterId, List<String> dataIds);

    /**
     * 查询章节资料
     *
     * @param chapterId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<CourseData> findByChapterId(String chapterId);

}
