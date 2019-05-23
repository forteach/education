package com.forteach.education.course.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.domain.CourseChapterReview;
import com.forteach.education.course.domain.CourseChapterReviewDescribe;
import com.forteach.education.course.domain.builder.CourseChapterReviewBuilder;
import com.forteach.education.course.domain.builder.CourseChapterReviewDescribeBuilder;
import com.forteach.education.course.dto.ICourseChapterReviewCountDto;
import com.forteach.education.course.dto.IStudentDto;
import com.forteach.education.course.repository.CourseChapterReviewDescribeRepository;
import com.forteach.education.course.repository.CourseChapterReviewRepository;
import com.forteach.education.course.service.CourseChapterReviewService;
import com.forteach.education.course.web.req.CourseChapterReviewSaveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 11:12
 * @version: 1.0
 * @description: 课程章节评价
 */
@Service
@Slf4j
public class CourseChapterReviewServiceImpl implements CourseChapterReviewService {

    private final CourseChapterReviewRepository courseChapterReviewRepository;

    private final CourseChapterReviewDescribeRepository courseChapterReviewDescribeRepository;

    @Autowired
    public CourseChapterReviewServiceImpl(CourseChapterReviewRepository courseChapterReviewRepository, CourseChapterReviewDescribeRepository courseChapterReviewDescribeRepository) {
        this.courseChapterReviewRepository = courseChapterReviewRepository;
        this.courseChapterReviewDescribeRepository = courseChapterReviewDescribeRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebResult save(CourseChapterReviewSaveReq reviewSaveReq) {
        Long count = courseChapterReviewDescribeRepository.countByIsValidatedEqualsAndChapterIdAndStudentId(TAKE_EFFECT_OPEN, reviewSaveReq.getChapterId(), reviewSaveReq.getStudentId());
        if (count != null && count > 0) {
            return WebResult.failException("您已经评价过本章节");
        }

        CourseChapterReviewDescribe courseChapterReviewDescribe = courseChapterReviewDescribeRepository
                .save(CourseChapterReviewDescribeBuilder
                        .aCourseChapterReviewDescribe()
                        .withChapterId(reviewSaveReq.getChapterId())
                        .withScore(reviewSaveReq.getScore())
                        .withStudentId(reviewSaveReq.getStudentId())
                        .withCreateUser(reviewSaveReq.getStudentId())
                        .withUpdateUser(reviewSaveReq.getStudentId())
                        .build());

        if (courseChapterReviewDescribe != null) {
            ICourseChapterReviewCountDto iCourseChapterReviewCountDto = courseChapterReviewDescribeRepository.averageScoreAndSum(reviewSaveReq.getChapterId());
            courseChapterReviewRepository.save(CourseChapterReviewBuilder
                    .aCourseChapterReview()
                    .withAverageScore(iCourseChapterReviewCountDto.getAverageScore() != null ? NumberUtil.roundStr(iCourseChapterReviewCountDto.getAverageScore(), 1) : "0")
                    .withChapterId(reviewSaveReq.getChapterId())
                    .withUpdateUser(reviewSaveReq.getStudentId())
                    .withCreateUser(reviewSaveReq.getStudentId())
                    .withReviewAmount(iCourseChapterReviewCountDto.getReviewAmount())
                    .build()
            );
            return WebResult.okResult();
        }
        return WebResult.failException("保存失败");
    }

    @Override
    public CourseChapterReview findChapterReview(String chapterId) {
        CourseChapterReview courseChapterReview = courseChapterReviewRepository.findByIsValidatedEqualsAndChapterId(TAKE_EFFECT_OPEN, chapterId);
        courseChapterReview.setCreateTime(null);
        courseChapterReview.setIsValidated(null);
        courseChapterReview.setUpdateUser(null);
        courseChapterReview.setUpdateTime(null);
        courseChapterReview.setCreateUser(null);
        return courseChapterReview;
    }

    @Override
    public List<IStudentDto> findCourseChapterStudentsAll(String chapterId) {
        return courseChapterReviewDescribeRepository.findCourseChapterReviewByChapterId(chapterId);
    }
}
