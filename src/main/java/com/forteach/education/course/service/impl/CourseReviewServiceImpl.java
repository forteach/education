package com.forteach.education.course.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseReviewDescribe;
import com.forteach.education.course.dto.ICourseReviewDto;
import com.forteach.education.course.repository.CourseRepository;
import com.forteach.education.course.repository.CourseReviewDescribeRepository;
import com.forteach.education.course.service.CourseReviewService;
import com.forteach.education.course.web.req.CourseReviewReq;
import com.forteach.education.course.web.res.CourseReviewDescResp;
import com.forteach.education.course.web.res.CourseReviewResp;
import com.forteach.education.util.UpdateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Optional;

import static com.forteach.education.common.keyword.Dic.*;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-9 10:14
 * @version: 1.0
 * @description: 课程评论
 */
@Service
public class CourseReviewServiceImpl implements CourseReviewService {

//    private final CourseRepository courseRepository;

    @Resource
    private CourseRepository courseRepository;

    @Resource
    private CourseReviewDescribeRepository courseReviewDescribeRepository;
//    private final CourseReviewDescribeRepository courseReviewDescribeRepository;

    @Resource
    private HashOperations<String, String, String> hashOperations;
//
//    private CourseReviewServiceImpl(CourseRepository courseRepository, CourseReviewDescribeRepository courseReviewDescribeRepository, HashOperations<String, String, String> hashOperations) {
//        this.courseRepository = courseRepository;
//        this.courseReviewDescribeRepository = courseReviewDescribeRepository;
//        this.hashOperations = hashOperations;
//    }


    /**
     * 学生评论及老师回复
     *
     * @param courseReviewDescribe
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseReviewDescribe save(CourseReviewDescribe courseReviewDescribe) {
        CourseReviewDescribe describe = null;
        if (StrUtil.isNotBlank(courseReviewDescribe.getReviewId())) {
            Optional<CourseReviewDescribe> optional = courseReviewDescribeRepository.findById(courseReviewDescribe.getReviewId());
            if (optional.isPresent()){
                CourseReviewDescribe reviewDescribe = optional.get();
                UpdateUtil.copyNullProperties(courseReviewDescribe, reviewDescribe);
                describe = courseReviewDescribeRepository.save(reviewDescribe);
            }
        }else {
            describe = courseReviewDescribeRepository.save(courseReviewDescribe);
        }
        //　修改课程评价人数和平均评分
        if (StrUtil.isBlank(courseReviewDescribe.getReply())) {
            courseRepository.findById(courseReviewDescribe.getCourseId()).ifPresent(c -> {
                Double score = courseReviewDescribeRepository.avgByCourseId(courseReviewDescribe.getCourseId());
                c.setAverageScore(score != null ? NumberUtil.roundStr(score, 1) : "0");
                c.setReviewAmount(c.getReviewAmount() != null ? c.getReviewAmount() + 1 : 1);
                courseRepository.save(c);
            });
        }
        return describe;
    }

    /**
     * 逻辑删除评论内容
     *
     * @param reviewId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteReview(String reviewId) {
        courseReviewDescribeRepository.findById(reviewId).ifPresent(c -> {
            c.setIsValidated(TAKE_EFFECT_CLOSE);
            courseReviewDescribeRepository.save(c);
        });
    }

    @Override
    public CourseReviewResp findFirstReview(String courseId) {
        CourseReviewDescribe courseReviewDescribe = courseReviewDescribeRepository.findFirstByIsValidatedEqualsAndCourseIdOrderByCreateTimeDesc(TAKE_EFFECT_OPEN, courseId);
        Course course = courseRepository.findByCourseId(courseId);
        if (course != null) {
            CourseReviewDescResp courseRevieDescResp = new CourseReviewDescResp();
            UpdateUtil.copyNullProperties(courseReviewDescribe, courseRevieDescResp);
            String key = STUDENT_ADO.concat(courseRevieDescResp.getStudentId());
            courseRevieDescResp.setStudentName(hashOperations.get(key, "name"));
            courseRevieDescResp.setPortrait(hashOperations.get(key, "portrait"));
            return CourseReviewResp.builder()
                    .averageScore(course.getAverageScore())
                    .courseId(courseId)
                    .reviewAmount(course.getReviewAmount())
                    .courseReviewDescribe(courseRevieDescResp)
                    .build();
        }
        return null;
    }

    @Override
    public Page<ICourseReviewDto> findReviewListPage(CourseReviewReq reviewReq) {
        Page<ICourseReviewDto> page =  courseReviewDescribeRepository.findCourseReviewListByCourseIdOrderByCreateTime(reviewReq.getCourseId(),
                PageRequest.of(reviewReq.getSortVo().getPage(), reviewReq.getSortVo().getSize()));
        return page;
    }


//    private CourseReviewDescResp builderCourseRevieDescResp(ICourseReviewDto iCourseReviewDto){
//        return CourseReviewDescResp.builder()
//                .studentId(iCourseReviewDto.getStudentId())
//                .studentName(iCourseReviewDto.getStudentName())
//                .portrait(StrUtil.isNotBlank(iCourseReviewDto.getPortrait()) ? iCourseReviewDto.getPortrait() :
//                        hashOperations.get(STUDENT_ADO.concat(iCourseReviewDto.getStudentId()), "portrait"))
//                .reviewId(iCourseReviewDto.getReviewId())
//                .reviewDescribe(iCourseReviewDto.getReviewDescribe())
//                .classId(iCourseReviewDto.getClassId())
//                .className(iCourseReviewDto.getClassName())
//                .createTime(iCourseReviewDto.getCreateTime())
//                .score(iCourseReviewDto.getScore())
//                .teacherId(iCourseReviewDto.getTeacherId())
//                .teacherName(iCourseReviewDto.getTeacherName())
//                .reply(iCourseReviewDto.getReply())
//                .replyTime(iCourseReviewDto.getReplyTime())
//                .build();
//    }

}
