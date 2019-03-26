package com.forteach.education.databank.service;

import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.databank.web.res.DatumResp;
import com.forteach.education.web.vo.DataDatumVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 11:05
 * @Version: 1.0
 * @Description:
 */
public interface ChapteDataService {

    public String save(String courseId, String chapterId, String datumArea, String datumType, String teachShare, String stuShare, List<DataDatumVo> files);

    /**
     * 修改资料领域和共享可见信息
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

    //课程资料详细列表
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable);

    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable);

    public void delete(CourseData chapteData);

    public void deleteById(String dataId);

}
