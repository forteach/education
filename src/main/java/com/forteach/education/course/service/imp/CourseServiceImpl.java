package com.forteach.education.course.service.imp;

import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseImages;
import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.repository.CourseImagesRepository;
import com.forteach.education.course.repository.CourseRepository;
import com.forteach.education.course.repository.ziliao.ImpCoursewareRepoitory;
import com.forteach.education.course.service.CourseService;
import com.forteach.education.course.service.CourseShareService;
import com.forteach.education.course.web.req.CourseFindAllReq;
import com.forteach.education.course.web.res.CourseListResp;
import com.forteach.education.course.web.res.CourseResp;
import com.forteach.education.course.web.vo.RCourse;
import com.forteach.education.course.web.res.CourseSaveResp;
import com.forteach.education.util.SortUtil;

import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.course.web.req.CourseSaveReq;
import com.forteach.education.web.vo.DataDatumVo;
import com.forteach.education.common.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.*;

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
//课程基本信息
    @Resource
    private CourseRepository courseRepository;

    @Resource
    private CourseImagesRepository courseImagesRepository;

    @Resource
    private CourseShareService courseShareService;

    /**
     * 保存课程科目信息，和协作信息
     * @param courseReq
     * @return
     */
    @Override
    @Transactional(rollbackForClassName="Exception")
    public CourseSaveResp save(CourseSaveReq courseReq) {
        String lessonPre = courseReq.getCourse().getLessonPreparationType();
        RCourse rcourse=courseReq.getCourse();
        Course course=new Course();
        UpdateUtil.copyNullProperties(rcourse, course);
        course = courseRepository.save(course);
        String shareId="";
        if (LESSON_PREPARATION_TYPE_GROUP.equals(lessonPre)) {
            shareId= courseShareService.save(course, courseReq.getTeachers());
        }

        //创建输出课程对象
        CourseSaveResp courseSaveResp=CourseSaveResp.builder()
                .courseId(course.getCourseId())
                .build();
        courseSaveResp.setShareId(shareId);
        return courseSaveResp;
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
    public void deleteById(String courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
    public CourseSaveResp edit(CourseSaveReq courseReq) {
        RCourse rcourse=courseReq.getCourse();
        Course course=new Course();
        UpdateUtil.copyNullProperties(rcourse, course);
        String courseId = course.getCourseId();
        Course source = courseRepository.findById(courseId).get();
        UpdateUtil.copyNullProperties(source, course);
        course.setCTime(source.getCTime());
        //保存课程信息
        courseRepository.save(course);

        //判断原有的备课类型是否是集体备课

        courseShareService.update(source.getLessonPreparationType(),courseReq.getOldShareId(),course, courseReq.getTeachers());

        CourseSaveResp courseSaveResp=CourseSaveResp.builder()
                .courseId(course.getCourseId())
                .build();

        return courseSaveResp;
    }


    @Override
    public List<CourseListResp> findAll(CourseFindAllReq courseFindAllReq) {
        SortVo sortVo=courseFindAllReq.getSortVo();
        PageRequest req=PageRequest.of(sortVo.getPage(), sortVo.getSize());
        return courseRepository.findByIsValidatedEquals(sortVo.getIsValidated(),req )
                .getContent().stream()
                .map((item)->{return new CourseListResp(item.getCourseId(),item.getCourseName(),item.getCourseNumber(),item.getLessonPreparationType(),item.getTopPicSrc());})
                .collect(toList());
}

    @Override
    public CourseResp getCourseById(String courseId) {

        Course course = courseRepository.findById(courseId).get();
        String shareId="";
        //课程为集体备课
        if(course.getLessonPreparationType().equals(LESSON_PREPARATION_TYPE_GROUP)){
            CourseShare cs= courseShareService.findByAndCourseIdAndAndShareArea(course.getCourseId());
            shareId=cs.getShareId();
        }

        return new CourseResp(course.getCourseId(),
                course.getCourseName(),
                course.getCourseNumber(),
                course.getLessonPreparationType(),
                course.getTeachingType(),
                course.getTopPicSrc(),
                course.getShareType(),
                course.getCourseDescribe(),
                shareId);

    }


    @Override
    @Transactional(rollbackForClassName="Exception")
    public void deleteIsValidById(String courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setIsValidated(TAKE_EFFECT_CLOSE);
        courseRepository.save(course);
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
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

    /**
     * 分页查询我的课程科目
     * @param sortVo
     * @return
     */
    @Override
    public Page<Course> findMyCourse(SortVo sortVo){
        //TODO 查询用户ID
        String cUser = "string";
        return courseRepository.findByIsValidatedEqualsAndCUser(sortVo.getIsValidated(), cUser, PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }


}
