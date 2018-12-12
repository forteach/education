package com.forteach.education.service.impl;

import com.forteach.education.domain.CourseChapter;
import com.forteach.education.dto.CourseChapterDto;
import com.forteach.education.repository.CourseChapterRepository;
import com.forteach.education.service.CourseChapterService;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseChapterEditReq;
import com.forteach.education.web.req.CourseDataDatumReq;
import com.forteach.education.web.resp.CourseTreeResp;
import com.forteach.education.web.resp.State;
import com.forteach.education.web.vo.CourseChapterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.forteach.education.common.Dic.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 17:14
 * @Version: 1.0
 * @Description: 科目章节管理
 */
@Slf4j
@Service
public class CourseChapterServiceImpl implements CourseChapterService {


    private final CourseChapterRepository courseChapterRepository;

    @Autowired
    public CourseChapterServiceImpl(CourseChapterRepository courseChapterRepository) {
        this.courseChapterRepository = courseChapterRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseChapter save(CourseChapter courseChapter) {
        //判断是顶层章节
//        if (COURSE_CHAPTER_CHAPTER_PARENT_ID.equals(courseChapter.getChapterParentId())){
//            courseChapter.setChapterLevel(1);
//        }else {
//
//            List<CourseChapterDto> courseChapterDtos = courseChapterRepository.findByChapterParentId(courseChapter.getCourseId(), courseChapter.getChapterParentId());
//        }
        return courseChapterRepository.save(courseChapter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseChapter edit(CourseChapterEditReq courseChapterEditReq) {
        CourseChapter source = courseChapterRepository.findById(courseChapterEditReq.getChapterId()).get();
        CourseChapter courseChapter = CourseChapter.builder().build();
        BeanUtils.copyProperties(courseChapterEditReq, courseChapter);
        UpdateUtil.copyNullProperties(source, courseChapter);
        return courseChapterRepository.save(courseChapter);
    }

    @Override
    public CourseChapter getCourseChapterById(String chapterId) {
        return courseChapterRepository.findById(chapterId).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CourseChapter courseChapter) {
        courseChapterRepository.delete(courseChapter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String chapterId) {
        courseChapterRepository.deleteById(chapterId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String chapterId) {
        CourseChapter courseChapter = courseChapterRepository.findById(chapterId).get();
        courseChapter.setIsValidated(TAKE_EFFECT_CLOSE);
        courseChapterRepository.save(courseChapter);
    }

    /**
     * 根据科目ID查询章节信息
     * 客户端用
     * @param courseId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseTreeResp> findByCourseId(String courseId){
        List<CourseChapterDto> dtoList = courseChapterRepository.findByCourseId(courseId);
        State state = new State();
        List<CourseTreeResp> list =  dtoList.stream().map(courseChapterDto -> {
            CourseTreeResp  courseTreeResp = new CourseTreeResp();
            courseTreeResp.setId(courseChapterDto.getChapterId());
            courseTreeResp.setText(courseChapterDto.getChapterName());
            courseTreeResp.setParent(courseChapterDto.getChapterParentId());
            if (PUBLISH_YES.equals(courseChapterDto.getPublish())) {
                courseTreeResp.setIcon("fa fa-briefcase icon-state-success");
            }else if (PUBLISH_NO.equals(courseChapterDto.getPublish())){
                courseTreeResp.setIcon("fa fa-send-o icon-state-success");
            }
            if (COURSE_CHAPTER_SORT == courseChapterDto.getSort()){
                state.setSelected(true);
            }else {
                state.setSelected(false);
            }
            courseTreeResp.setState(state);
            return courseTreeResp;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * 通过父章节目录信息查询子小节信息
     * @param chapterParentId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseChapterDto> findByChapterParentId(String chapterParentId){
        return courseChapterRepository.findByChapterParentId("", chapterParentId);
    }
    /**
     * １. 章节ID
     * ２．是否有效　0 有效　1无效
     * 根据条件筛选查询对应的章节信息
     * @param vo
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseChapter> findAllCourseChapter(CourseChapterVo vo){
        return courseChapterRepository.findAllCourseChapterByChapterIdAndIsValidated(vo.getIsValidated(), vo.getCourseId());
    }

    @Override
    public void saveCourseDataDatum(CourseDataDatumReq courseDataDatumReq) {

    }
}
