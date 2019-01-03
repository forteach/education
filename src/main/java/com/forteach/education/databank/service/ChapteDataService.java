package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.ziliao.ChapteData;
import com.forteach.education.databank.dto.IChapterDataCountDto;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.databank.web.res.ChapteDataResp;
import org.springframework.data.domain.Page;
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

   //public String edit(ChapteData chapteData);

    //ChapteData findById(String dataId);

    public void delete(ChapteData chapteData);

    void deleteById(String dataId);

    //课程资料列表
    public Page<ChapteData> findDataPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable);

    //课程资料列表
    public List<ChapteDataResp> findDataList(String courseId, String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable);
    //教案数量
    public int countJiaoan(String courseId);
    //课件数量
    public List<IChapterDataCountDto> countKeJian(String courseId);
}
