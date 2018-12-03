package com.forteach.education.service.impl;

import com.forteach.education.domain.FileDatum;
import com.forteach.education.repository.FileDatumRepository;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.util.FileUtils;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseDataDatumReq;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

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
        fileDatum.setFileType(FileUtils.ext(fileDatum.getFileName()));
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
        FileDatum source = fileDatumRepository.findById(fileDatum.getFileId()).get();
        UpdateUtil.copyNullProperties(source, fileDatum);
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

    /**
     * 根据章节ID查询对应的文件信息
     *
     * @param chapterId
     * @return
     */
    @Override
    public List<FileDatum> findByChapterId(String chapterId) {
        return fileDatumRepository.findByIsValidatedEqualsAndChapterId(TAKE_EFFECT_OPEN, chapterId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCourseDataDatum(CourseDataDatumReq courseDataDatumReq) {
        List<FileDatum> fileDatumArrayList = new ArrayList<>();
        courseDataDatumReq.getFiles().forEach(file -> {
            final boolean add = fileDatumArrayList.add(FileDatum.builder()
                    .fileId(file.getFileId())
                    .chapterId(courseDataDatumReq.getChapterId())
                    .courseId(courseDataDatumReq.getCourseId())
                    .fileName(file.getFileName())
                    .fileUrl(file.getFilePath())
                    //获取文件后缀名判断文件类型
                    .fileType(FileUtils.ext(file.getFileName()))
                    .build());
        });
        fileDatumRepository.saveAll(fileDatumArrayList);
    }

    /**
     * 根据科目课程ID查询资料信息
     * @param courseId
     * @return
     */
    @Override
    public List<FileDatum> findFileDatumByCourseId(String courseId) {
        return fileDatumRepository.findByIsValidatedAndCourseId(TAKE_EFFECT_OPEN, courseId);
    }
}
