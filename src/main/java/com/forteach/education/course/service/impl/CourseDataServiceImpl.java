package com.forteach.education.course.service.impl;

import cn.hutool.core.util.IdUtil;
import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.course.domain.ziliao.CourseDatumArea;
import com.forteach.education.course.repository.ziliao.CourseDataRepository;
import com.forteach.education.course.repository.ziliao.CourseDatumAreaRepository;
import com.forteach.education.course.service.CourseDataService;
import com.forteach.education.course.web.req.CourseDataDeleteReq;
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
import java.util.Objects;
import java.util.stream.Collectors;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;
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

        //4、根据资料主表的资料ID，结合所选的单个资料领域，修改文件资料表的资料领域字段
        courseDataRepository.updateDatumArea(fileId, datumArea);
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
        return courseDataRepository.findByChapterIdAndDatumTypeAndKNodeIdAndIsValidatedOrderByCreateTimeAsc(chapterId, datumType, kNodeId, TAKE_EFFECT_OPEN, pageable).getContent()
                .stream()
                .filter(Objects::nonNull)
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
        List<DatumResp> list = courseDataRepository.findByChapterIdAndDatumTypeAndIsValidatedOrderByCreateTimeAsc(chapterId, datumType, TAKE_EFFECT_OPEN, pageable).getContent()
                .stream()
                .filter(Objects::nonNull)
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
        List<DatumResp> list = courseDataRepository.findByChapterIdAndIsValidatedOrderByCreateTimeAsc(chapterId, TAKE_EFFECT_OPEN, pageable).getContent()
                .stream()
                .filter(Objects::nonNull)
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseData(CourseDataDeleteReq courseDataDeleteReq) {
        //修改资料表无效
        List<CourseDatumArea> courseDatumAreas = courseDatumAreaRepository.findByChapterId(courseDataDeleteReq.getChapterId())
                .stream()
                .filter(Objects::nonNull)
                .map(courseDatumArea -> {
                    if (!courseDataDeleteReq.getFileIds().isEmpty() && courseDataDeleteReq.getFileIds().contains(courseDatumArea.getDataId())) {
                        courseDatumArea.setIsValidated(TAKE_EFFECT_CLOSE);
                    } else {
                        courseDatumArea.setIsValidated(TAKE_EFFECT_CLOSE);
                    }
                    return courseDatumArea;
                }).collect(toList());
        courseDatumAreaRepository.saveAll(courseDatumAreas);
        //修改
        List<CourseData> courseDataList = courseDataRepository.findByChapterId(courseDataDeleteReq.getChapterId())
                .stream().filter(Objects::nonNull)
                .map(courseData -> {
                    if (!courseDataDeleteReq.getFileIds().isEmpty() && courseDataDeleteReq.getFileIds().contains(courseData.getDataId())) {
                        courseData.setIsValidated(TAKE_EFFECT_CLOSE);
                    } else {
                        courseData.setIsValidated(TAKE_EFFECT_CLOSE);
                    }
                    return courseData;
                }).collect(toList());
        courseDataRepository.saveAll(courseDataList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourseData(CourseDataDeleteReq courseDataDeleteReq) {
        courseDataRepository.deleteByChapterIdAndDataIdIn(courseDataDeleteReq.getChapterId(), courseDataDeleteReq.getFileIds());
        courseDatumAreaRepository.deleteByChapterIdAndFileIdIn(courseDataDeleteReq.getChapterId(), courseDataDeleteReq.getFileIds());
    }
}