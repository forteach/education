package com.forteach.education.service;

import com.forteach.education.domain.ChapteData;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 11:05
 * @Version: 1.0
 * @Description:
 */
public interface ChapteDataService {

    ChapteData save(ChapteData chapteData);

    ChapteData edit(ChapteData chapteData);

    ChapteData findById(String dataId);

    void delete(ChapteData chapteData);

    void deleteById(String dataId);

    List<ChapteData> findByCourseId(String courseId);

    List<ChapteData> findByChapterId(String chapterId);
}
