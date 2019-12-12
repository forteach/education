package com.forteach.education.course.service;

import com.forteach.education.course.domain.CourseChapter;
import com.forteach.education.course.dto.ICourseChapterDto;
import com.forteach.education.course.web.req.CourseChapterEditReq;
import com.forteach.education.course.web.res.CourseChapterSaveResp;
import com.forteach.education.web.resp.CourseTreeResp;
import com.forteach.education.web.vo.CourseChapterVo;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 17:09
 * @Version: 1.0
 * @Description: 科目章节操作service
 */
public interface CourseChapterService {

    public CourseChapterSaveResp save(CourseChapter courseChapter);

    public CourseChapterSaveResp edit(CourseChapterEditReq courseChapterEditReq);

    public CourseChapterSaveResp getCourseChapterById(String chapterId);

    public void delete(CourseChapter courseChapter);

    public void deleteById(String chapterId);

    public void deleteIsValidById(String chapterId);

    public List<CourseTreeResp> findByCourseId(String courseId);

    public List<ICourseChapterDto> findByChapterParentId(String isValidated, String chapterParentId);

    public List<ICourseChapterDto> findAllCourseChapter(CourseChapterVo vo);

    Optional<CourseChapter> findById(String chapterId);
}
