package com.forteach.education.service;

import com.forteach.education.domain.CourseChapter;
import com.forteach.education.dto.CourseChapterDto;
import com.forteach.education.web.req.CourseChapterEditReq;
import com.forteach.education.web.resp.CourseTreeResp;
import com.forteach.education.web.vo.CourseChapterVo;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 17:09
 * @Version: 1.0
 * @Description: 科目章节操作service
 */
public interface CourseChapterService {

    CourseChapter save(CourseChapter courseChapter);

    CourseChapter edit(CourseChapterEditReq courseChapterEditReq);

    CourseChapter getCourseChapterById(String chapterId);

    void delete(CourseChapter courseChapter);

    void deleteById(String chapterId);

    void deleteIsValidById(String chapterId);

    List<CourseTreeResp> findByCourseId(String courseId);

    List<CourseChapterDto> findByChapterParentId(String chapterParentId);

    List<CourseChapter> findAllCourseChapter(CourseChapterVo vo);
}
