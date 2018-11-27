package com.forteach.education.service.impl;

import com.forteach.education.domain.ChapteData;
import com.forteach.education.repository.ChapteDataRepository;
import com.forteach.education.service.ChapteDataService;
import com.forteach.education.util.UpdateTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 11:04
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class ChapteDataServiceImpl implements ChapteDataService {

    private final ChapteDataRepository chapteDataRepository;

    @Autowired
    public ChapteDataServiceImpl(ChapteDataRepository chapteDataRepository) {
        this.chapteDataRepository = chapteDataRepository;
    }

    @Override
    public ChapteData save(ChapteData chapteData) {
        return chapteDataRepository.save(chapteData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public ChapteData edit(ChapteData chapteData) {
        ChapteData source = chapteDataRepository.findById(chapteData.getDataId()).get();
        UpdateTool.copyNullProperties(source, chapteData);
        return chapteDataRepository.save(chapteData);
    }

    @Override
    public ChapteData findById(String dataId) {
        return chapteDataRepository.findById(dataId).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(ChapteData chapteData) {
        chapteDataRepository.delete(chapteData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String dataId) {
        chapteDataRepository.deleteById(dataId);
    }

    @Override
    public List<ChapteData> findByCourseId(String courseId) {
        return chapteDataRepository.findByIsValidatedEqualsAndCourseId(TAKE_EFFECT_OPEN, courseId);
    }

    @Override
    public List<ChapteData> findByChapterId(String chapterId) {
        return chapteDataRepository.findByIsValidatedEqualsAndChapterId(TAKE_EFFECT_OPEN, chapterId);
    }
}
