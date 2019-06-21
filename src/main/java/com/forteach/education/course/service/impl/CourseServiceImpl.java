package com.forteach.education.course.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseEntity;
import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.dto.ICourseListDto;
import com.forteach.education.course.dto.ICourseStudyDto;
import com.forteach.education.course.repository.*;
import com.forteach.education.course.service.CourseService;
import com.forteach.education.course.service.CourseShareService;
import com.forteach.education.course.web.req.CourseImagesReq;
import com.forteach.education.course.web.res.CourseListResp;
import com.forteach.education.images.course.domain.CourseImages;
import com.forteach.education.images.course.service.CourseImagesService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.forteach.education.common.keyword.Dic.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:59
 * @Version: 1.0
 * @Description:　课程管理
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    /**
     * 课程基本信息
     */
    @Resource
    private CourseRepository courseRepository;

    /**
     * 课程轮播图
     */
    @Resource
    private CourseImagesService courseImagesService;

    /**
     * 集体备课课程共享资源
     */
    @Resource
    private CourseShareService courseShareService;

    @Resource
    private TeacherClassCourseRepository teacherClassCourseRepository;

    @Resource
    private CourseEntrityRepository courseEntrityRepository;

    @Resource
    private CourseReviewDescribeRepository courseReviewDescribeRepository;

    @Resource
    private CourseStudyRepository courseStudyRepository;

    /**
     * 保存课程基本信息
     *
     * @param course   课程基本信息
     * @param teachers 集体备课教师信息
     * @return 课程编号和集体备课资源编号
     */
    @Override
    @Transactional(rollbackForClassName = "Exception")
    public List<String> save(Course course, List<RTeacher> teachers) {
        //1、保存课程基本信息
        if (StrUtil.isBlank(course.getCourseId())) {
            course.setCourseId(IdUtil.fastSimpleUUID());
        }
        course = courseRepository.save(course);

        //2、如果是集体备课，保存集体备课基本信息
        String shareId = "";
        if (LESSON_PREPARATION_TYPE_GROUP.equals(course.getLessonPreparationType())) {
            MyAssert.isTrue(teachers.size()==0, DefineCode.ERR0010,"集体备课，必须选择备课教师");
            shareId = courseShareService.save(course, teachers);
        }

        //3、设置返回数据
        List<String> result = new ArrayList<String>();
        result.add(course.getCourseId());
        result.add(shareId);
        return result;
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public String edit(Course course, String oldShareId, List<RTeacher> teachers) {

        //修改课程信息
        courseRepository.save(course);

        //判断原有的备课类型是否是集体备课，并修改集体备课信息
        return courseShareService.update(course.getLessonPreparationType(), oldShareId, course, teachers);
    }

    /**
     * 获得所有可用课程列表
     *
     * @param page
     * @return
     */
    @Override
    public List<ICourseListDto> findAll(PageRequest page) {
        return courseRepository.findByIsValidated(Dic.TAKE_EFFECT_OPEN, page)
                .getContent();
    }

    @Override
    public Course findByCourseId(String courseId){
        return courseRepository.findByCourseId(courseId);
    }

    /**
     * 分页查询我的课程科目
     *
     * @param page
     * @return
     */
    @Override
    public List<ICourseListDto> findMyCourse(String userId, PageRequest page) {
        return courseRepository.findByCreateUserAndIsValidatedOrderByCreateTimeDesc(userId, TAKE_EFFECT_OPEN, page)
                .getContent();
    }

    /**
     * 根据课程编号，获得课程基本信息
     *
     * @param id
     * @return
     */
    @Override
    public Course getById(String id) {
        return courseRepository.findById(id)
                .orElse(new Course());
    }


    /**
     * 学生端查询我的课程信息
     * @param classId
     * @return
     */
    @Override
    @Cacheable(value = "myCourseList", key = "#classId", sync = true, unless = "#result eq null")
    public List<CourseListResp> myCourseList(String classId) {
        List<CourseListResp> listRespList = Lists.newArrayList();
        courseRepository.findByIsValidatedEqualsAndCourseIdInOrderByCreateTime(classId)
                .stream()
                .filter(Objects::nonNull)
                .forEach(iCourseChapterListDto -> {
                    listRespList.add(CourseListResp.builder()
                            .courseId(iCourseChapterListDto.getCourseId())
                            .courseName(iCourseChapterListDto.getCourseName())
                            .alias(iCourseChapterListDto.getAlias())
                            .topPicSrc(iCourseChapterListDto.getTopPicSrc())
                            .courseDescribe(iCourseChapterListDto.getCourseDescribe())
                            .joinChapterId(iCourseChapterListDto.getChapterId())
                            .joinChapterName(iCourseChapterListDto.getChapterName())
                            .teacherId(iCourseChapterListDto.getTeacherId())
                            .teacherName(iCourseChapterListDto.getTeacherName())
                            .build());
                });
        return listRespList;
    }

    /**
     * 根据课程ID，获得课程基本信息和集体备课共享编号
     *
     * @param courseId
     * @return
     */
    @Override
    public Map<String, Object> getCourseById(String courseId) {
        Map<String, Object> result = new HashMap<>(2);
        courseRepository.findById(courseId).ifPresent(course -> {
            String shareId = "";
            //课程为集体备课
            if (course.getLessonPreparationType().equals(LESSON_PREPARATION_TYPE_GROUP)) {
                CourseShare cs = courseShareService.findByCourseIdAll(course.getCourseId());
                shareId = cs.getShareId();
            }
            result.put("course", course);
            result.put("shareId", shareId);
        });
        return result;
    }




    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void deleteIsValidById(String courseId) {
        courseRepository.findById(courseId)
                .ifPresent(course -> {
                    course.setIsValidated(TAKE_EFFECT_CLOSE);
                    courseRepository.save(course);
                });
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void saveCourseImages(CourseImagesReq courseImagesReq) {
        courseImagesService.saveCourseImages(courseImagesReq.getCourseId(),courseImagesReq.getImages());
    }


    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void deleteById(String courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    /**
     * 查询封面图片信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<CourseImages> findImagesByCourseId(String courseId) {
        return courseImagesService.findImagesByCourseId(courseId);
    }


    /**
     * 查询同步过来的课程信息
     * @return
     */
    @Override
    @Cacheable(value = "allCourseEntity", key = "#root.targetClass", unless = "#result eq null ")
    public List<CourseEntity> findCourseList() {
        return courseEntrityRepository.findByIsValidated(TAKE_EFFECT_OPEN);
    }

    @Override
    public List<ICourseStudyDto> findCourseStudyList(String studentId, Integer studyStatus) {
        return courseStudyRepository.findByIsValidatedEqualsAndStudentId(studentId, studyStatus);
    }


}
