package com.forteach.education.course.service.imp;


import cn.hutool.core.util.StrUtil;
import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.domain.CourseShareUsers;
import com.forteach.education.course.repository.CourseShareRepository;
import com.forteach.education.course.repository.CourseShareUsersRepository;
import com.forteach.education.course.service.CourseShareService;
import com.forteach.education.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.COURSE_SHARE_AREA_ALL;
import static com.forteach.education.common.keyword.Dic.LESSON_PREPARATION_TYPE_GROUP;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-5 15:36
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class CourseShareServiceImpl implements CourseShareService {

    @Resource
    private CourseShareRepository courseShareRepository;

    @Resource
    private CourseShareUsersRepository courseShareUsersRepository;

    @Override
    public List<CourseShareUsers> findByShareId(String shareId) {
        return courseShareUsersRepository.findByShareId(shareId);
    }

    /**
     * 存储集体备课
     * @param course  课程基本信息
     * @param teacherList  教师备课成员
     * @return  ShareId 课程分享编号
     */
    @Transactional(rollbackForClassName="Exception")
    @Override
    public String save( Course course, List<RTeacher> teacherList) {

            //保存课程分享范围
            CourseShare courseShare= CourseShare.builder()
                    .shareArea(COURSE_SHARE_AREA_ALL)//分享整套课程
                    .build();
          courseShare.setCreateTime(course.getCreateTime());
          UpdateUtil.copyNullProperties(course, courseShare);
          courseShareRepository.save(courseShare);


            //是协作处理
            List<CourseShareUsers> list = new ArrayList<>();
                teacherList.forEach(teacher -> {
                    CourseShareUsers cs= CourseShareUsers.builder()
                           // .shareId(courseShare.getShareId())  //获得分享编号
                            .userId(teacher.getTeacherId())  //用户编号
                            .userName(teacher.getTeacherName())
                            .build();
                    cs.setCreateTime(courseShare.getCreateTime());
                    UpdateUtil.copyNullProperties(courseShare, cs);
                list.add(cs);
            });
            courseShareUsersRepository.saveAll(list);
            //是集体备课返回课程分享编号
            return courseShare.getShareId();
        }

    /**
     *
     * @param shareId  原有课程的共享编号
     * @param course
     * @param teacherList
     * @return  ShareId 新生成课程分享编号
     */
//    @Transactional(rollbackOn = Exception.class)
    @Transactional(rollbackForClassName="Exception")
    @Override
    public String update(String lessonPreType,String shareId, Course course, List<RTeacher> teacherList) {
        if (LESSON_PREPARATION_TYPE_GROUP.equals(lessonPreType) ) {
            //判断是否存在共享范围编码是否存在
            if (StrUtil.isNotBlank(shareId)) {

                //删除分享范围相关的用户
                courseShareUsersRepository.deleteByShareId(shareId);

                //删除课程分享范围记录
                courseShareRepository.deleteById(shareId);

            }

            //判断并保存集体备课教师和课程共享信息
            return save(course, teacherList);
        }
        return "";
    }

    @Override
    public  CourseShare findByAndCourseIdAndAndShareArea(String courseId ){
       return  courseShareRepository.findByAndCourseIdAndAndShareArea(courseId,COURSE_SHARE_AREA_ALL);
    }
}
