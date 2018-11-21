package com.forteach.education.service.impl;

import com.forteach.education.domain.CourseChapter;
import com.forteach.education.repository.CourseChapterRepository;
import com.forteach.education.service.CourseChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 17:14
 * @Version: 1.0
 * @Description: 科目章节管理
 */
public class CourseChapterServiceImpl implements CourseChapterService {

    @Autowired
    private CourseChapterRepository courseChapterRepository;

    @Override
    public CourseChapter save(CourseChapter courseChapter) {
        return courseChapterRepository.save(courseChapter);
    }

    @Override
    public CourseChapter edit(CourseChapter courseChapter) {
        return courseChapterRepository.save(courseChapter);
    }

    @Override
    public CourseChapter getCourseChapterById(String courseId) {
        return courseChapterRepository.findById(courseId).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CourseChapter courseChapter) {
        courseChapterRepository.delete(courseChapter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String courseId) {
        courseChapterRepository.deleteById(courseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String courseId) {
        CourseChapter courseChapter = courseChapterRepository.findById(courseId).get();
        courseChapter.setIsValidated(TAKE_EFFECT_CLOSE);
        courseChapterRepository.save(courseChapter);
    }
}
