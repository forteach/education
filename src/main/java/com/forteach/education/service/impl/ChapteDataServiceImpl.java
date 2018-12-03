package com.forteach.education.service.impl;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.*;
import com.forteach.education.repository.*;
import com.forteach.education.service.ChapteDataService;
import com.forteach.education.util.FileUtils;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.ChapteDataReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private FileDatumRepository fileDatumRepository;
    @Resource
    private AudioDatumRepository audioDatumRepository;
    @Resource
    private LinkDatumRepository linkDatumRepository;
    @Resource
    private ViewDatumRepository viewDatumRepository;
    @Resource
    private PhotoSortRepository photoSortRepository;
    @Resource
    private PhotosRepository photosRepository;

    @Autowired
    public ChapteDataServiceImpl(ChapteDataRepository chapteDataRepository) {
        this.chapteDataRepository = chapteDataRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebResult save(ChapteDataReq chapteDataReq) {
        //1文档　2图册　3视频　4音频　5链接
        int datumType = chapteDataReq.getDatumType();
        List<ChapteData> chapteDataList = new ArrayList<>();
        switch (datumType){
            case 1:
                List<FileDatum> fileDatumList = new ArrayList<>();
                chapteDataReq.getFiles().forEach(file -> {
                    fileDatumList.add(
                    FileDatum.builder()
                            .courseId(chapteDataReq.getCourseId())
                            .fileType(FileUtils.ext(file.getFileName()))
                            .fileName(file.getFileName())
                            .fileUrl(file.getFilePath())
                            .chapterId(chapteDataReq.getChapterId())
                            .build());
                });
                List<FileDatum> list = fileDatumRepository.saveAll(fileDatumList);
                list.forEach(fileDatum -> {
                    chapteDataList.add(
                    ChapteData.builder()
                            .fileId(fileDatum.getFileId())
                            .datumName(fileDatum.getFileName())
                            .chapterId(chapteDataReq.getChapterId())
                            .courseId(chapteDataReq.getCourseId())
                            .datumArea(chapteDataReq.getDatumArea())
                            .datumType(datumType)
                            .remark(chapteDataReq.getRemark())
                            .build()
                    );
                });
                break;
            case 2:
                PhotoSort photoSort = photoSortRepository.save(PhotoSort.builder()
                        .courseId(chapteDataReq.getCourseId())
                        .sortImgType(1)
                        .topPicSrc(chapteDataReq.getFiles().size() > 0 ? chapteDataReq.getFiles().get(0).getFilePath() : null)
                        .sortImgName(chapteDataReq.getFiles().size() > 0 ? chapteDataReq.getFiles().get(0).getFileName() : null)
                        .build());
                List<Photos> photosList = new ArrayList<>();
                chapteDataReq.getFiles().forEach(file -> {
                    photosList.add(
                            Photos.builder()
                                    .sortImgId(photoSort.getSortImgId())
                                    .photoSrc(file.getFilePath())
                                    .photoName(file.getFileName())
                                    .build());
                });
                photosRepository.saveAll(photosList);
                chapteDataList.add(ChapteData.builder()
                        .sortImgId(photoSort.getSortImgId())
                        .datumName(photoSort.getSortImgName())
                        .chapterId(chapteDataReq.getChapterId())
                        .courseId(chapteDataReq.getCourseId())
                        .datumArea(chapteDataReq.getDatumArea())
                        .datumType(datumType)
                        .remark(chapteDataReq.getRemark())
                        .build());
                break;
            case 3:
                List<ViewDatum> viewDatas = new ArrayList<ViewDatum>();
                chapteDataReq.getFiles().forEach(file -> {
                    viewDatas.add(ViewDatum.builder()
                            .chapterId(chapteDataReq.getChapterId())
                            .viewName(file.getFileName())
                            .viewType(FileUtils.ext(file.getFileName()))
                            .viewUrl(file.getFilePath())
                            .courseId(chapteDataReq.getCourseId())
                            .build());
                });
                List<ViewDatum> viewDatumList = viewDatumRepository.saveAll(viewDatas);
                viewDatumList.forEach(viewDatum -> {
                    chapteDataList.add(ChapteData.builder()
                                    .viewId(viewDatum.getViewId())
                                    .datumName(viewDatum.getViewName())
                                    .chapterId(chapteDataReq.getChapterId())
                                    .courseId(chapteDataReq.getCourseId())
                                    .datumArea(chapteDataReq.getDatumArea())
                                    .datumType(datumType)
                                    .remark(chapteDataReq.getRemark())
                                    .build());
                });
                break;
            case 4:
                List<AudioDatum> audioDatas = new ArrayList<AudioDatum>();
                chapteDataReq.getFiles().forEach(file -> {
                    audioDatas.add(AudioDatum.builder()
                            .audioName(file.getFileName())
                            .audioType(FileUtils.ext(file.getFileName()))
                            .audioUrl(file.getFilePath())
                            .chapterId(chapteDataReq.getChapterId())
                            .build());
                });
                List<AudioDatum> audioDatumList = audioDatumRepository.saveAll(audioDatas);
                audioDatumList.forEach(audioData ->{
                    chapteDataList.add(ChapteData.builder()
                            .audioId(audioData.getAudioId())
                            .datumName(audioData.getAudioName())
                            .chapterId(chapteDataReq.getChapterId())
                            .courseId(chapteDataReq.getCourseId())
                            .datumArea(chapteDataReq.getDatumArea())
                            .datumType(datumType)
                            .remark(chapteDataReq.getRemark())
                            .build());
                });
                break;
            case 5:
                List<LinkDatum> linkDatumList = new ArrayList<LinkDatum>();
                chapteDataReq.getFiles().forEach(link -> {
                    linkDatumList.add(LinkDatum.builder()
                            .chapterId(chapteDataReq.getChapterId())
                            .linkName(link.getFileName())
                            .linkUrl(link.getFilePath())
                            .build());
                });
                List<LinkDatum> linkDatas = linkDatumRepository.saveAll(linkDatumList);
                linkDatas.forEach(linkDatum -> {
                    chapteDataList.add(ChapteData.builder()
                            .linkId(linkDatum.getLinkId())
                            .datumName(linkDatum.getLinkName())
                            .chapterId(chapteDataReq.getChapterId())
                            .courseId(chapteDataReq.getCourseId())
                            .datumArea(chapteDataReq.getDatumArea())
                            .datumType(datumType)
                            .remark(chapteDataReq.getRemark())
                            .build());
                });
                break;
                default:
                    return WebResult.failException("文件类型参数错误");
        }
        return WebResult.okResult(chapteDataRepository.saveAll(chapteDataList));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public ChapteData edit(ChapteData chapteData) {
        ChapteData source = chapteDataRepository.findById(chapteData.getDataId()).get();
        UpdateUtil.copyNullProperties(source, chapteData);
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
