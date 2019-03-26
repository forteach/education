package com.forteach.education.course.service.impl;

import cn.hutool.core.util.IdUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.course.domain.ziliao.CourseDatumArea;
import com.forteach.education.course.repository.ziliao.CourseDataRepository;
import com.forteach.education.course.repository.ziliao.CourseDatumAreaRepository;
import com.forteach.education.course.service.CourseDataService;
import com.forteach.education.course.web.vo.RCourseData;
import com.forteach.education.databank.web.res.DatumResp;
import com.forteach.education.util.FileUtils;
import com.forteach.education.util.UpdateUtil;
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
 * @Description: 课程资料挂载
 */
@Service
@Slf4j
public class CourseDataServiceImpl implements CourseDataService {

    @Resource
    private CourseDataRepository courseDataRepository;

    @Resource
    private CourseDatumAreaRepository courseDatumAreaRepository;

    /**
     * 保存课程挂在文件
     *
     * @param files 需要添加挂载的文件列表
     * @return int 添加的文件数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(String chapterId, List<RCourseData> files) {
        //1、按课程章节编号，删除历史挂在记录
        courseDatumAreaRepository.deleteByChapterId(chapterId);
        courseDataRepository.deleteByChapterId(chapterId);

        //2、添加为挂载资料文件列表明细
        List<CourseData> fileDatumList = files.stream()
                .map(item -> {
                    CourseData cd = new CourseData();
                    cd.setDataId(item.getDataId());
                    UpdateUtil.copyNullProperties(item, cd);
                    cd.setDatumExt(FileUtils.ext(cd.getDatumName()));
                    return cd;
                }).collect(Collectors.toList());

        courseDataRepository.saveAll(fileDatumList);

        //3、添加文件所属领域信息--不经常频繁的添加资料
        fileDatumList.stream().forEach((absDatum) ->
        {
            final String id = absDatum.getDataId();
            final String type = absDatum.getDatumType();
            final String knodeId = absDatum.getKNodeId();
            List<CourseDatumArea> list = new ArrayList<CourseDatumArea>();
            Arrays.stream(absDatum.getDatumArea().split(",")).forEach((area) -> {
                CourseDatumArea da = new CourseDatumArea();
                da.setFileId(id);
                da.setDatumArea(area);
                da.setDatumType(type);
                da.setChapterId(chapterId);
                da.setKNodeId(knodeId);
                list.add(da);

            });
            courseDatumAreaRepository.saveAll(list);
        });

        return fileDatumList.size();
    }

    /**
     * 单个资料领域修改
     *
     * @param fileId     资料主表主键编号
     * @param datumType  资料类型
     * @param datumArea  资料领域
     * @param teachShare 教师分享
     * @param stuShare   学生可见
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateAreaAndShare(String courseId, String chapterId, String kNodeId, String fileId, String datumType, String datumArea, String teachShare, String stuShare) {
        //1、根据资料编号和领域编号，获得领域表信息
        CourseDatumArea da = courseDatumAreaRepository.findByFileIdAndDatumArea(fileId, datumArea);

        //2、如果存在就删除，相反就添加
        if (da != null) {
            courseDatumAreaRepository.deleteByFileIdAndDatumArea(fileId, datumArea);
        } else {
            da = new CourseDatumArea();
            da.setFileId(IdUtil.fastSimpleUUID());
            da.setDatumArea(datumArea);
            da.setDatumType(datumType);
            da.setKNodeId(kNodeId);
            da.setChapterId(chapterId);
            da.setCourseId(courseId);
            courseDatumAreaRepository.save(da);
        }

        //3、根据资料ID，获得领域信息，组装成都好分割的字符串
        List<String> list = courseDatumAreaRepository.findByFileId(fileId).stream().map(item -> {
            return item.getDatumArea();
        }).collect(Collectors.toList());
        String newArea = String.join(",", list);

        //4、根据资料主表的资料ID，结合所选的单个资料领域，修改文件资料表的资料领域字段
        courseDataRepository.updateDatumArea(fileId, newArea);
        courseDataRepository.updateStuShare(fileId, stuShare);
        courseDataRepository.updateTeachShare(fileId, teachShare);

        return "ok";
    }

    /**
     * 获得按资料领域、课程章节、知识点、资料列表
     *
     * @param chapterId
     * @param kNodeId
     * @param datumType
     * @param pageable
     * @return
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable) {

        //转换LIST对象
        return courseDataRepository.findByChapterIdAndDatumTypeAndKNodeIdAndIsValidatedOrderByCreateTimeAsc(chapterId, datumType, kNodeId, Dic.TAKE_EFFECT_OPEN, pageable).getContent()
                .stream()
                .map((item) -> {
                    DatumResp dr = new DatumResp();
                    dr.setChapterId(item.getChapterId());
                    dr.setFileName(item.getDatumName());
                    dr.setFileName(item.getDatumArea());
                    dr.setFileType(item.getDatumExt());
                    dr.setFileId(item.getDataId());
                    dr.setFileUrl(item.getDatumUrl());
                    dr.setDatumArea(item.getDatumArea());
                    dr.setStuShare(item.getStuShare());
                    dr.setTeachShare(item.getTeachShare());
                    return dr;
                }).collect(toList());
    }

    /**
     * 获得按资料领域、课程章节、资料列表
     *
     * @param chapterId
     * @param datumType
     * @param pageable
     * @return
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, String datumType, Pageable pageable) {

        //再根据资料编号查找资料信息，转换LIST对象
        List<DatumResp> list = courseDataRepository.findByChapterIdAndDatumTypeAndIsValidatedOrderByCreateTimeAsc(chapterId, datumType, Dic.TAKE_EFFECT_OPEN, pageable).getContent()
                .stream()
                .map((item) -> {
                    DatumResp dr = new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    dr.setFileId(item.getDataId());
                    dr.setFileName(item.getDatumName());
                    dr.setFileUrl(item.getDatumUrl());
                    return dr;
                }).collect(toList());

        return list;
    }

    /**
     * 获得按资料领域、课程章节、资料列表
     *
     * @param chapterId
     * @param pageable
     * @return
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, Pageable pageable) {

        //再根据资料编号查找资料信息，转换LIST对象
        List<DatumResp> list = courseDataRepository.findByChapterIdAndIsValidatedOrderByCreateTimeAsc(chapterId, Dic.TAKE_EFFECT_OPEN, pageable).getContent()
                .stream()
                .map((item) -> {
                    DatumResp dr = new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    dr.setFileId(item.getDataId());
                    dr.setFileName(item.getDatumName());
                    dr.setFileUrl(item.getDatumUrl());
                    return dr;
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
