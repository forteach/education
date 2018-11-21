package com.forteach.education.service.impl;

import com.forteach.education.domain.ViewDatum;
import com.forteach.education.repository.ViewDatumRepository;
import com.forteach.education.service.ViewDatumService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;

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

    @Autowired
    private ViewDatumRepository viewDatumRepository;

    @Override
    public ViewDatum save(ViewDatum viewDatum) {
        return viewDatumRepository.save(viewDatum);
    }

    @Override
    public ViewDatum edit(ViewDatum viewDatum) {
        viewDatum.setCTime(new Date());
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
//        viewDatum.setUTime(new Date());
        viewDatum.setIsValidated(TAKE_EFFECT_CLOSE);
        viewDatumRepository.save(viewDatum);
    }
}