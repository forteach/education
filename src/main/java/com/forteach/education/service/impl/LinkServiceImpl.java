package com.forteach.education.service.impl;

import com.forteach.education.domain.LinkDatum;
import com.forteach.education.repository.LinkDatumRepository;
import com.forteach.education.service.LinkDatumService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 20:52
 * @Version: 1.0
 * @Description: 链接资料库
 */
@Slf4j
@Service
public class LinkServiceImpl implements LinkDatumService {

    @Autowired
    private LinkDatumRepository linkDatumRepository;

    @Override
    public LinkDatum save(LinkDatum linkDatum) {
        return linkDatumRepository.save(linkDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LinkDatum edit(LinkDatum linkDatum) {
        return linkDatumRepository.save(linkDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(LinkDatum linkDatum) {
        linkDatumRepository.delete(linkDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String linkId) {
        linkDatumRepository.deleteById(linkId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String linkId) {
        LinkDatum linkDatum = linkDatumRepository.findById(linkId).get();
        linkDatum.setIsValidated(TAKE_EFFECT_CLOSE);
        linkDatumRepository.save(linkDatum);
    }

    @Override
    public Page<LinkDatum> findAll(SortVo sortVo) {
        return linkDatumRepository.findByIsValidatedEquals(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }

    @Override
    public LinkDatum getLinkDatumById(String linkId) {
        return linkDatumRepository.findById(linkId).get();
    }

    /**
     * 根据章节ID查询对应的链接信息
     * @param chapterId
     * @return
     */
    @Override
    public List<LinkDatum> findByChapterId(String chapterId) {
        return linkDatumRepository.findByIsValidatedEqualsAndChapterId(TAKE_EFFECT_OPEN, chapterId);
    }
}
