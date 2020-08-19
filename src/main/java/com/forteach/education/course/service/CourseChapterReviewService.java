package com.forteach.education.course.service;

import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.dto.IStudentDto;
import com.forteach.education.course.web.req.CourseChapterReviewSaveReq;
import com.forteach.education.course.web.res.CourseChapterReviewDescribeResp;
import com.forteach.education.course.web.res.CourseChapterReviewResp;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 11:12
 * @version: 1.0
 * @description:
 */
public interface CourseChapterReviewService {

    /**
     * 保存评论评分
     * @param reviewSaveReq
     * @return
     */
    WebResult save(CourseChapterReviewSaveReq reviewSaveReq);

    /**
     * 查找课程章节信息
     * @param chapterId
     * @return
     */
    CourseChapterReviewResp findChapterReview(String chapterId);

    /**
     * 查询评论过的学生信息
     * @param chapterId
     * @return
     */
    List<IStudentDto> findCourseChapterStudentsAll(String chapterId);

    CourseChapterReviewDescribeResp findMyCourseChapterReview(String studentId, String chapterId);
}
