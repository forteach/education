package com.forteach.education.databank.service;


import com.forteach.education.databank.domain.ViewDatum;
import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Page;


import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/19 20:54
 * @Version: 1.0
 * @Description: 视频资料库
 */
public interface ViewDatumService {

    ViewDatum save(ViewDatum viewDatum);

    ViewDatum edit(ViewDatum viewDatum);

    void delete(ViewDatum viewDatum);

    void deleteById(String viewDatumId);

    ViewDatum getViewDatumById(String viewDatumId);

    Page<ViewDatum> findAll(SortVo sortVo);

    void deleteIsValidById(String viewId);

    List<ViewDatum> findByChapterId(String chapterId);

    List<ViewDatum> findViewDatumByCourseId(String courseId);

}
