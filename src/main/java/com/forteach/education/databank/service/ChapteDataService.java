package com.forteach.education.databank.service;

import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.databank.web.res.DatumResp;
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

    public String save(ChapteDataReq chapteDataReq);

    //课程资料详细列表
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable);

    public List<DatumResp> findDatumList(String chapterId, String kNodeId,String datumArea, String datumType, Pageable pageable);

    public void delete(CourseData chapteData);

    public void deleteById(String dataId);

}
