package com.forteach.education.course.service;

import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.course.web.req.CourseDataDeleteReq;
import com.forteach.education.course.web.vo.RCourseData;
import com.forteach.education.databank.web.res.DatumResp;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 11:05
 * @Version: 1.0
 * @Description:课程备课资料操作
 */
public interface CourseDataService {

    /**
     * 保存课程挂在文件
     *
     * @param chapterId
     * @param files
     * @return
     */
    public int save(String chapterId, List<RCourseData> files);

    /**
     * 单个资料领域修改
     *
     * @param courseId
     * @param chapterId
     * @param kNodeId
     * @param fileId
     * @param datumType
     * @param datumArea
     * @param teachShare
     * @param stuShare
     * @return
     */
    public String updateAreaAndShare(String courseId, String chapterId, String kNodeId, String fileId, String datumType, String datumArea, String teachShare, String stuShare);

    /**
     * 课程资料详细列表
     *
     * @param chapterId
     * @param datumType
     * @param pageable
     * @return
     */
    public List<DatumResp> findDatumList(String chapterId, String datumType, Pageable pageable);

    /**
     * 课程资料详细列表
     *
     * @param chapterId
     * @param pageable
     * @return
     */
    public List<DatumResp> findDatumList(String chapterId, Pageable pageable);

    /**
     * 获得按资料领域、课程章节、知识点、资料列表
     *
     * @param chapterId
     * @param kNodeId
     * @param datumType
     * @param pageable
     * @return
     */
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable);

    public void delete(CourseData chapteData);

    public void deleteById(String dataId);

    /**
     * 逻辑删除挂接的课程资料信息
     *
     * @param courseDataDeleteReq
     */
    void removeCourseData(CourseDataDeleteReq courseDataDeleteReq);

    /**
     * 物理删除需要挂接的文件信息
     *
     * @param courseDataDeleteReq
     */
    void deleteCourseData(CourseDataDeleteReq courseDataDeleteReq);
}
