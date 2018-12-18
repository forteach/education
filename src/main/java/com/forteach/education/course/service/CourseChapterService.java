package com.forteach.education.course.service;

import com.forteach.education.course.domain.CourseChapter;
import com.forteach.education.course.dto.CourseChapterDto;
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

    public CourseChapter save(CourseChapter courseChapter);

    public CourseChapter edit(CourseChapterEditReq courseChapterEditReq);

    public CourseChapter getCourseChapterById(String chapterId);

    public void delete(CourseChapter courseChapter);

    public void deleteById(String chapterId);

    public void deleteIsValidById(String chapterId);

    public  List<CourseTreeResp> findByCourseId(String courseId);

    public List<CourseChapterDto> findByChapterParentId(String chapterParentId);

    public List<CourseChapter> findAllCourseChapter(CourseChapterVo vo);
}
