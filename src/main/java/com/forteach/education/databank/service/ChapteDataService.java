package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.ziliao.ChapteData;
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

    public void delete(ChapteData chapteData);

    public void deleteById(String dataId);


    //课程资料详细列表
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable);

    public List<DatumResp> findDatumList(String chapterId, String kNodeId,String datumArea, String datumType, Pageable pageable);

    //获得图集列表
    public List<DatumResp> findPhotoList(String chapterId, String kNodeId, String datumType, Pageable pageable);


    //课程资料列表
//    public Page<ChapteData> findDataPage(String courseId, String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable);

}
