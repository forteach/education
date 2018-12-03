package com.forteach.education.service.impl;

import com.forteach.education.domain.Course;
import com.forteach.education.domain.CourseImages;
import com.forteach.education.domain.CourseShare;
import com.forteach.education.repository.CourseImagesRepository;
import com.forteach.education.repository.CourseRepository;
import com.forteach.education.repository.CourseShareRepository;
import com.forteach.education.service.CourseService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.web.req.CourseReq;
import com.forteach.education.web.vo.DataDatumVo;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.Dic.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:59
 * @Version: 1.0
 * @Description:　科目管理
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseRepository courseRepository;

    @Resource
    private CourseImagesRepository courseImagesRepository;

    @Resource
    private CourseShareRepository courseShareRepository;

    /**
     * 保存课程科目信息，和协作信息
     * @param courseReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public Course save(CourseReq courseReq) {
        int shareType = courseReq.getCourse().getShareType();
        Course course = courseRepository.saveAndFlush(courseReq.getCourse());
        if (COURSE_SHARE_TYPE_COOPERATION == shareType){
            //是协作处理
            List<CourseShare> list = new ArrayList<>();
            courseReq.getTeachers().forEach(teacher -> {
                list.add(CourseShare.builder()
                        .courseId(course.getCourseId())
                        .shareArea(1)
                        .shareId(course.getCUser())
                        .build());
            });
            courseShareRepository.saveAll(list);
        }
        return course;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Course edit(Course course) {
        Course source = courseRepository.findById(course.getCourseId()).get();
        UpdateUtil.copyNullProperties(source, course);
        return courseRepository.save(course);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setIsValidated(TAKE_EFFECT_CLOSE);
        courseRepository.save(course);
    }

    @Override
    public Page<Course> findAll(SortVo sortVo) {
        return courseRepository.findByIsValidatedEquals(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCourseImages(CourseImagesReq courseImagesReq){
        List<CourseImages> list = new ArrayList<>();
        List<DataDatumVo> dataDatumVos = courseImagesReq.getImages();
        for (int i = 0; i < dataDatumVos.size(); i++) {
            DataDatumVo dataDatumVo = dataDatumVos.get(i);
            list.add(CourseImages.builder()
                    .courseId(courseImagesReq.getCourseId())
                    .indexNum(i + 1)
                    .imageName(dataDatumVo.getFileName())
                    .imageUrl(dataDatumVo.getFilePath())
                    .build());
        }
        courseImagesRepository.saveAll(list);
    }

    /**
     * 查询封面图片信息
     * @param courseId
     * @return
     */
    @Override
    public List<CourseImages> findImagesByCourseId(String courseId) {
        return courseImagesRepository.findByIsValidatedEqualsAndCourseIdOrderByIndexNumAsc(TAKE_EFFECT_OPEN, courseId);
    }
}
