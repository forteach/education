package com.forteach.education.service.impl;

import com.forteach.education.domain.CourseChapter;
import com.forteach.education.dto.CourseChapterDto;
import com.forteach.education.repository.CourseChapterRepository;
import com.forteach.education.service.CourseChapterService;
import com.forteach.education.util.UpdateTool;
import com.forteach.education.web.vo.CourseChapterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;

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
    public CourseChapter edit(CourseChapter courseChapter) {
        CourseChapter source = courseChapterRepository.findById(courseChapter.getChapterId()).get();
        UpdateTool.copyNullProperties(source, courseChapter);
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
     * 根据科目ID查询有效的第一层章节信息
     * 客户端用
     * @param courseId
     * @return
     */
    @Override
    public List<CourseChapterDto> findByCourseId(String courseId){
        return courseChapterRepository.findByCourseId(courseId);
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
}
