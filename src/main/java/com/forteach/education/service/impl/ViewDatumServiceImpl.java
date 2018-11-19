package com.forteach.education.service.impl;

import com.forteach.education.domain.ViewDatum;
import com.forteach.education.repository.ViewDatumRepository;
import com.forteach.education.service.ViewDatumService;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

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
        return viewDatumRepository.save(viewDatum);
    }

    @Override
    public void delete(ViewDatum viewDatum) {
        viewDatumRepository.delete(viewDatum);
    }

    @Override
    public void deleteById(String viewDatumId) {
        viewDatumRepository.deleteById(viewDatumId);
    }

    @Override
    public ViewDatum getViewDatumById(String viewDatumId) {
        return viewDatumRepository.findById(viewDatumId).get();
    }

    @Override
    public Page<ViewDatum> findAll(SortVo sortVo) {
        Sort sort = new Sort(Sort.Direction.DESC, sortVo.getSorting());
        return viewDatumRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN, PageRequest.of(sortVo.getPage(), sortVo.getSize(), sort));
    }
}
