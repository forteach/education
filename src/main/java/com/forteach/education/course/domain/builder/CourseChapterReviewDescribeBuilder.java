package com.forteach.education.course.domain.builder;

import com.forteach.education.course.domain.CourseChapterReviewDescribe;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 18:37
 * @version: 1.0
 * @description:
 */
public final class CourseChapterReviewDescribeBuilder {
    public String createUser;
    public String updateUser;
    private String chapterId;
    private String studentId;
    private Integer score;

    private CourseChapterReviewDescribeBuilder() {
    }

    public static CourseChapterReviewDescribeBuilder aCourseChapterReviewDescribe() {
        return new CourseChapterReviewDescribeBuilder();
    }

    public CourseChapterReviewDescribeBuilder withChapterId(String chapterId) {
        this.chapterId = chapterId;
        return this;
    }

    public CourseChapterReviewDescribeBuilder withStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public CourseChapterReviewDescribeBuilder withCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public CourseChapterReviewDescribeBuilder withScore(Integer score) {
        this.score = score;
        return this;
    }

    public CourseChapterReviewDescribeBuilder withUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public CourseChapterReviewDescribe build() {
        CourseChapterReviewDescribe courseChapterReviewDescribe = new CourseChapterReviewDescribe();
        courseChapterReviewDescribe.setChapterId(chapterId);
        courseChapterReviewDescribe.setStudentId(studentId);
        courseChapterReviewDescribe.setCreateUser(createUser);
        courseChapterReviewDescribe.setScore(score);
        courseChapterReviewDescribe.setUpdateUser(updateUser);
        return courseChapterReviewDescribe;
    }
}
