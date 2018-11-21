package com.forteach.education.service;

import com.forteach.education.domain.CourseChapter;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 17:09
 * @Version: 1.0
 * @Description: 科目章节操作service
 */
public interface CourseChapterService {

    CourseChapter save(CourseChapter courseChapter);

    CourseChapter edit(CourseChapter courseChapter);

    CourseChapter getCourseChapterById(String courseId);

    void delete(CourseChapter courseChapter);

    void deleteById(String courseId);

    void deleteIsValidById(String courseId);
}
