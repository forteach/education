package com.forteach.education.databank.service.imp;

import com.forteach.education.databank.domain.ViewDatum;
import com.forteach.education.databank.repository.ViewDatumRepository;
import com.forteach.education.databank.service.ViewDatumService;
import com.forteach.education.util.FileUtils;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.common.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/19 20:53
 * @Version: 1.0
 * @Description: 视频资料库service
 */
@Service
@Slf4j
public class ViewDatumServiceImpl implements ViewDatumService {

    private final ViewDatumRepository viewDatumRepository;

    @Autowired
    public ViewDatumServiceImpl(ViewDatumRepository viewDatumRepository) {
        this.viewDatumRepository = viewDatumRepository;
    }

    @Override
    public ViewDatum save(ViewDatum viewDatum) {
        if (viewDatum.getViewType() == null) {
            viewDatum.setViewType(FileUtils.ext(viewDatum.getViewName()));
        }
        return viewDatumRepository.save(viewDatum);
    }

    @Override
    public ViewDatum edit(ViewDatum viewDatum) {
        ViewDatum source = viewDatumRepository.findById(viewDatum.getViewId()).get();
        UpdateUtil.copyNullProperties(source, viewDatum);
        return viewDatumRepository.save(viewDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(ViewDatum viewDatum) {
        viewDatumRepository.delete(viewDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String viewDatumId) {
        viewDatumRepository.deleteById(viewDatumId);
    }

    @Override
    public ViewDatum getViewDatumById(String viewDatumId) {
        return viewDatumRepository.findById(viewDatumId).get();
    }

    @Override
    public Page<ViewDatum> findAll(SortVo sortVo) {
        return viewDatumRepository.findByIsValidatedEquals(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }

    /**
     * 逻辑删除视频信息使其无效不显示
     * @param viewId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String viewId) {
        ViewDatum viewDatum = viewDatumRepository.findById(viewId).get();
        viewDatum.setIsValidated(TAKE_EFFECT_CLOSE);
        viewDatumRepository.save(viewDatum);
    }

    /**
     * 根据章节信息查询对应的视频信息
     * @param chapterId
     * @return
     */
    @Override
    public List<ViewDatum> findByChapterId(String chapterId) {
        return viewDatumRepository.findByIsValidatedEqualsAndChapterId(TAKE_EFFECT_OPEN, chapterId);
    }

    @Override
    public List<ViewDatum> findViewDatumByCourseId(String courseId) {
        return viewDatumRepository.findByIsValidatedEqualsAndCourseIdAndChapterIdIsNull(TAKE_EFFECT_OPEN, courseId);
    }
}
