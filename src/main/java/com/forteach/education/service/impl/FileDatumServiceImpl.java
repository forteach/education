package com.forteach.education.service.impl;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.domain.ChapteData;
import com.forteach.education.domain.FileDatum;
import com.forteach.education.repository.ChapteDataRepository;
import com.forteach.education.repository.FileDatumRepository;
import com.forteach.education.repository.RepositoryImpl;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.util.FileUtils;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseDataDatumReq;
import com.forteach.education.web.req.CourseFileDataReq;
import com.forteach.education.web.req.CourseFileListReq;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Resource
    private FileDatumRepository fileDatumRepository;

    @Resource
    private RepositoryImpl repository;

    @Resource
    private ChapteDataRepository chapteDataRepository;

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
                    .mount(file.getMount())
                    .build());
        });
        //保存到章节科目数据表里
        List<FileDatum> list = fileDatumRepository.saveAll(fileDatumArrayList);
        List<ChapteData> chapteDataList = new ArrayList<>();
        list.forEach(fileDatum -> {
            chapteDataList.add(ChapteData.builder()
                    .datumName(fileDatum.getFileName())
                    .chapterId(courseDataDatumReq.getChapterId())
                    .courseId(courseDataDatumReq.getCourseId())
                    .fileId(fileDatum.getFileId())
                    .datumType(1)
                    .build());
        });
        chapteDataRepository.saveAll(chapteDataList);
    }

    /**
     * 根据科目课程ID查询资料信息
     * @param courseFileDataReq
     * @return
     */
    @Override
    public Page<FileDatum> findFileDatumByCourseId(CourseFileDataReq courseFileDataReq) {
        SortVo sortVo = courseFileDataReq.getSortVo();
        Page<FileDatum> page = repository.findChapteFiles(sortVo.getIsValidated(), courseFileDataReq.getCourseId(), courseFileDataReq.getChapterId(), courseFileDataReq.getMount(),
                PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
        return page;
    }

    /**
     * 修改课程科目的文件状态
     * @param courseFileListReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editCourseFileList(CourseFileListReq courseFileListReq) {
        //修改的文件列表
        List<FileDatum> fileDatumList2 = new ArrayList<>();
        //保存的文件列表
        List<FileDatum> fileDatumList1 = new ArrayList<>();
        courseFileListReq.getFileDatums().forEach(fileDatum -> {
            if (StrUtil.isBlank(fileDatum.getFileId())) {
                fileDatumList1.add(FileDatum.builder()
                        .fileName(fileDatum.getFileName())
                        .chapterId(fileDatum.getChapterId())
                        .courseId(fileDatum.getCourseId())
                        .fileUrl(fileDatum.getFileUrl())
                        .fileType(FileUtils.ext(fileDatum.getFileName()))
                        .mount(fileDatum.getMount())
                        .build());
            } else {
                FileDatum source = fileDatumRepository.findById(fileDatum.getFileId()).get();
                UpdateUtil.copyNullProperties(source, fileDatum);
                fileDatumList2.add(fileDatum);
            }
        });
        fileDatumRepository.saveAll(fileDatumList2);
        List<FileDatum> files = fileDatumRepository.saveAll(fileDatumList1);
        List<ChapteData> chapteDataList = new ArrayList<>();
        for (FileDatum fileDatum : files) {
            chapteDataList.add(ChapteData.builder()
                    .mount(fileDatum.getMount())
                    .courseId(fileDatum.getCourseId())
                    .chapterId(fileDatum.getChapterId())
                    .datumType(1)
                    .datumName(fileDatum.getFileName())
                    .fileId(fileDatum.getFileId())
                    .build());
        }
        chapteDataRepository.saveAll(chapteDataList);
    }

}
