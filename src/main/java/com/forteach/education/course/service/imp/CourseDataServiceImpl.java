package com.forteach.education.course.service.imp;

import cn.hutool.core.util.IdUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.course.domain.ziliao.CourseDatumArea;
import com.forteach.education.course.repository.ziliao.CourseDataRepository;
import com.forteach.education.course.repository.ziliao.CourseDatumAreaRepository;
import com.forteach.education.course.service.CourseDataService;
import com.forteach.education.databank.web.res.DatumResp;

import com.forteach.education.util.FileUtils;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseDataDatumReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 11:04
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class CourseDataServiceImpl implements CourseDataService {

    @Resource
    private CourseDataRepository courseDataRepository;

    @Resource
    private CourseDatumAreaRepository courseDatumAreaRepository;

    /**
     *
     * @param
     * @return  int 添加的文件数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(CourseDataDatumReq req) {
//        //1文档　　3视频　4音频　5链接
//        String datumType = chapteDataReq.getDatumType();
//        //添加资料文件关联记录
//        String dataId = IdUtil.fastSimpleUUID();
//        String chapterId=chapteDataReq.getChapterId();
//        String datumArea=chapteDataReq.getDatumArea();
//        String datumName=chapteDataReq.getDatumName();
//        String kNodeId=chapteDataReq.getKNodeId();

        //添加为挂载资料文件列表明细
        List<CourseData> fileDatumList=req.getFiles().stream()
                .map(item->{
                    CourseData cd=new  CourseData();
                    cd.setDataId(IdUtil.fastSimpleUUID());
                    UpdateUtil.copyNullProperties(item, cd);
                    cd.setDatumExt(FileUtils.ext(cd.getDatumName()));
                    return cd;
                }).collect(Collectors.toList());

        courseDataRepository.saveAll(fileDatumList);

        //添加文件所属领域信息--不经常频繁的添加资料
        fileDatumList.stream().forEach((absDatum)->
        {
            final  String id= absDatum.getDataId();
            final  String type=absDatum.getDatumType();
            final  String  chapterId=absDatum.getChapterId();
            final  String  knodeId=absDatum.getKNodeId();
            List<CourseDatumArea>  list=new ArrayList<CourseDatumArea>();
            Arrays.stream(absDatum.getDatumArea().split(",")).forEach((area)->{
                CourseDatumArea da=new CourseDatumArea();
                da.setFileId(id);
                da.setDatumArea(area);
                da.setDatumType(type);
                da.setChapterId(chapterId);
                da.setKNodeId(knodeId);
                list.add(da);

            });
            courseDatumAreaRepository.saveAll(list);
            log.info("-------{}",list.size());

        });

       return fileDatumList.size();
    }



    /**
     *   获得按资料领域、课程、章节、资料列表
     * @param chapterId
     * @param kNodeId
     * @param datumType
     * @param pageable
     * @return
     */
    @Override
        public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable){

        //转换LIST对象
        return courseDataRepository.findByChapterIdAndDatumTypeAndKNodeIdAndIsValidated(chapterId,datumType,kNodeId,Dic.TAKE_EFFECT_OPEN,pageable).getContent()
                .stream()
                .map((item)->{
                    DatumResp dr=new DatumResp();
                    dr.setChapterId(item.getChapterId());
                    dr.setFileName(item.getDatumName());
                    dr.setFileName(item.getDatumArea());
                    dr.setFileType(item.getDatumExt());
                    dr.setFileId(item.getDataId());
                    dr.setFileUrl(item.getDatumUrl());
                    dr.setDatumArea(item.getDatumArea());
                    dr.setStuShare(item.getStuShare());
                    dr.setTeachShare(item.getTeachShare());
                    return  dr;
                }).collect(toList());
    }

    @Override
        public List<DatumResp> findDatumList(String chapterId, String datumType, Pageable pageable) {

        //再根据资料编号查找资料信息，转换LIST对象
        List<DatumResp> list= courseDataRepository.findByChapterIdAndDatumTypeAndIsValidatedOrderByCreateTimeAsc(chapterId,datumType,Dic.TAKE_EFFECT_OPEN,pageable).getContent()
                .stream()
                .map((item)->{
                    DatumResp dr=new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    dr.setFileName(item.getDatumName());
                    dr.setFileUrl(item.getDatumUrl());
                    return  dr;
                }).collect(toList());

        return list;
    }

    @Override
    public void delete(CourseData chapteData) {

    }

    @Override
    public void deleteById(String dataId) {

    }


}
