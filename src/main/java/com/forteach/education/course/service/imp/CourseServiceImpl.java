package com.forteach.education.course.service.imp;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseImages;
import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.dto.ICourseListDto;
import com.forteach.education.course.repository.CourseImagesRepository;
import com.forteach.education.course.repository.CourseRepository;
import com.forteach.education.course.service.CourseService;
import com.forteach.education.course.service.CourseShareService;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.web.vo.DataDatumVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //课程基本信息
    @Resource
    private CourseRepository courseRepository;

    //课程轮播图
    @Resource
    private CourseImagesRepository courseImagesRepository;

    //集体备课课程共享资源
    @Resource
    private CourseShareService courseShareService;

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


    /**
     * 分页查询我的课程科目
     *
     * @param page
     * @return
     */
    @Override
    public List<ICourseListDto> findMyCourse(String userId, PageRequest page) {
        return courseRepository.findByCreateUserAndIsValidated(userId, TAKE_EFFECT_OPEN, page)
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
        return courseRepository.findById(id).get();
    }

    /**
     * 根据课程ID，获得课程基本信息和集体备课共享编号
     *
     * @param courseId
     * @return
     */
    @Override
    public Map<String, Object> getCourseById(String courseId) {

        Course course = courseRepository.findById(courseId).get();
        String shareId = "";
        //课程为集体备课
        if (course.getLessonPreparationType().equals(LESSON_PREPARATION_TYPE_GROUP)) {
            CourseShare cs = courseShareService.findByCourseIdAll(course.getCourseId());
            shareId = cs.getShareId();
        }

        Map result = new HashMap();
        result.put("course", course);
        result.put("shareId", shareId);
        return result;

    }


    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void deleteIsValidById(String courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setIsValidated(TAKE_EFFECT_CLOSE);
        courseRepository.save(course);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void saveCourseImages(CourseImagesReq courseImagesReq) {
        List<CourseImages> list = new ArrayList<>();
        List<DataDatumVo> dataDatumVos = courseImagesReq.getImages();
        for (int i = 0; i < dataDatumVos.size(); i++) {
            DataDatumVo dataDatumVo = dataDatumVos.get(i);
            list.add(CourseImages.builder()
                    .courseId(courseImagesReq.getCourseId())
                    .indexNum(i + 1)
                    .imageName(dataDatumVo.getFileName())
                    .imageUrl(dataDatumVo.getFileUrl())
                    .build());
        }
        courseImagesRepository.saveAll(list);
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
        return courseImagesRepository.findByIsValidatedEqualsAndCourseIdOrderByIndexNumAsc(TAKE_EFFECT_OPEN, courseId);
    }


}
