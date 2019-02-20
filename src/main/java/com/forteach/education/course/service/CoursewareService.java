package com.forteach.education.course.service;

import com.forteach.education.course.web.req.CoursewareAll;
import com.forteach.education.course.web.req.ImpCoursewareAll;

import java.util.List;

public interface CoursewareService {

    /*
    保存除图集以外，主要课件文件信息
     */
    public ImpCoursewareAll saveFile(ImpCoursewareAll obj);

    public List<CoursewareAll> saveCourseAtlit(ImpCoursewareAll obj);

    /**
     * 获得对应类型的重要课件信息数量和列表
     *
     * @param chapterId
     * @param importantType
     * @param datumType
     * @return
     */
    public ImpCoursewareAll getImpCourseware(String chapterId, String importantType, String datumType);

    public List<CoursewareAll> getCourseArlitsList(String chapterId);

    public List<CoursewareAll> getPhotoList(String arlitId);
}
