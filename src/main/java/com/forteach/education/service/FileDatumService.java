package com.forteach.education.service;

import com.forteach.education.domain.FileDatum;
import com.forteach.education.web.req.CourseDataDatumReq;
import com.forteach.education.web.req.CourseFileDataReq;
import com.forteach.education.web.req.CourseFileListReq;
import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 20:59
 * @Version: 1.0
 * @Description: 文档资料库
 */
public interface FileDatumService {

    FileDatum save(FileDatum fileDatum);

    void deleteById(String fileId);

    void delete(FileDatum fileDatum);

    FileDatum edit(FileDatum fileDatum);

    void deleteIsValidById(String fileId);

    Page<FileDatum> findAll(SortVo sortVo);

    FileDatum getFileDatumById(String fileId);

    List<FileDatum> findByChapterId(String chapterId);

    void saveCourseDataDatum(CourseDataDatumReq courseDataDatumReq);

    Page<FileDatum> findFileDatumByCourseId(CourseFileDataReq courseFileDataReq);

    void editCourseFileList(CourseFileListReq courseFileListReq);

}
