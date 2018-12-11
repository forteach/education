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

import java.util.ArrayList;
import java.util.List;

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
    public CourseChapter save(CourseChapter courseChapter) {
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
    public List<CourseTreeResp> findByCourseId(String courseId){
        List<CourseChapterDto> dtoList = courseChapterRepository.findByCourseId(courseId);
        List<CourseTreeResp> list = new ArrayList<>();
        CourseTreeResp courseTreeResp = null;
        State state = State.builder().build();
        for (CourseChapterDto courseChapterDto : dtoList) {
            if (courseChapterDto.getChapterParentId() == null) {
                list.add(
                        CourseTreeResp.builder()
                                .id(courseChapterDto.getChapterId())
                                .parent("#")
                                .text(courseChapterDto.getChapterName())
//                                .level(courseChapterDto.getChapterLevel())
                                .state(state)
                                .build()
                );
            } else {
                courseTreeResp = new CourseTreeResp();
                courseTreeResp.setId(courseChapterDto.getChapterId());
                courseTreeResp.setText(courseChapterDto.getChapterName());
                courseTreeResp.setParent(courseChapterDto.getChapterParentId());
                if (RELEASE_YES.equals(courseChapterDto.getRelease())) {
                    courseTreeResp.setIcon("fa fa-briefcase icon-state-success");
                }else if (RELEASE_NO.equals(courseChapterDto.getRelease())){
                    courseTreeResp.setIcon("fa fa-send-o icon-state-success");
                }
                courseTreeResp.setState(state);
                list.add(courseTreeResp);
            }
        }
        return list;
    }

    /**
     * 通过父章节目录信息查询子小节信息
     * @param chapterParentId
     * @return
     */
    @Override
    public List<CourseChapterDto> findByChapterParentId(String chapterParentId){
        return courseChapterRepository.findByChapterParentId(chapterParentId);
    }
    /**
     * １. 章节ID
     * ２．是否有效　0 有效　1无效
     * 根据条件筛选查询对应的章节信息
     * @param vo
     * @return
     */
    @Override
    public List<CourseChapter> findAllCourseChapter(CourseChapterVo vo){
        return courseChapterRepository.findAllCourseChapterByChapterIdAndIsValidated(vo.getIsValidated(), vo.getCourseId());
    }

    @Override
    public void saveCourseDataDatum(CourseDataDatumReq courseDataDatumReq) {

    }
}
