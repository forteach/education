package com.forteach.education.service.impl;

import com.forteach.education.domain.FileDatum;
import com.forteach.education.repository.FileDatumRepository;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 21:06
 * @Version: 1.0
 * @Description: 文档资料库
 */
@Slf4j
@Service
public class FileDatumServiceImpl implements FileDatumService {

    @Autowired
    private FileDatumRepository fileDatumRepository;

    @Override
    public FileDatum save(FileDatum fileDatum) {
        return fileDatumRepository.save(fileDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String fileId) {
        fileDatumRepository.deleteById(fileId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(FileDatum fileDatum) {
        fileDatumRepository.delete(fileDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileDatum edit(FileDatum fileDatum) {
        return fileDatumRepository.save(fileDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String fileId) {
        FileDatum fileDatum = fileDatumRepository.findById(fileId).get();
        fileDatum.setIsValidated(TAKE_EFFECT_CLOSE);
        fileDatumRepository.save(fileDatum);
    }

    @Override
    public Page<FileDatum> findAll(SortVo sortVo) {
        return fileDatumRepository.findByIsValidatedEquals(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }

    @Override
    public FileDatum getFileDatumById(String fileId) {
        return fileDatumRepository.findById(fileId).get();
    }
}
