package com.forteach.education.course.service;

import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.databank.web.res.DatumResp;
import com.forteach.education.web.req.CourseDataDatumReq;
import org.springframework.data.domain.Page;
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

    public int save(CourseDataDatumReq req);

    //课程资料详细列表
    public List<DatumResp> findDatumList(String chapterId, String datumType, Pageable pageable);

    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable);

    public void delete(CourseData chapteData);

    public void deleteById(String dataId);

}
