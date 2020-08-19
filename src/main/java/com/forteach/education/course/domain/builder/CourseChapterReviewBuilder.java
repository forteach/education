package com.forteach.education.course.domain.builder;

import com.forteach.education.course.domain.CourseChapterReview;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 18:11
 * @version: 1.0
 * @description:
 */
public final class CourseChapterReviewBuilder {
    public String createUser;
    public String updateUser;
    private String chapterId;
    private String averageScore;
    private Integer reviewAmount;

    private CourseChapterReviewBuilder() {
    }

    public static CourseChapterReviewBuilder aCourseChapterReview() {
        return new CourseChapterReviewBuilder();
    }


    public CourseChapterReviewBuilder withChapterId(String chapterId) {
        this.chapterId = chapterId;
        return this;
    }

    public CourseChapterReviewBuilder withAverageScore(String averageScore) {
        this.averageScore = averageScore;
        return this;
    }

    public CourseChapterReviewBuilder withCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public CourseChapterReviewBuilder withReviewAmount(Integer reviewAmount) {
        this.reviewAmount = reviewAmount;
        return this;
    }

    public CourseChapterReviewBuilder withUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public CourseChapterReview build() {
        CourseChapterReview courseChapterReview = new CourseChapterReview();
        courseChapterReview.setChapterId(chapterId);
        courseChapterReview.setAverageScore(averageScore);
        courseChapterReview.setCreateUser(createUser);
        courseChapterReview.setReviewAmount(reviewAmount);
        courseChapterReview.setUpdateUser(updateUser);
        return courseChapterReview;
    }
}
