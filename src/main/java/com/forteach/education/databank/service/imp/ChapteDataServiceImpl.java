package com.forteach.education.databank.service.imp;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.databank.domain.ziliao.*;
import com.forteach.education.databank.dto.IChapterDataCountDto;
import com.forteach.education.databank.repository.ziliao.*;
import com.forteach.education.databank.service.ChapteDataService;
import com.forteach.education.databank.web.res.ChapteDataResp;
import com.forteach.education.util.FileUtils;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.vo.DataDatumVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;
import static java.util.stream.Collectors.toList;

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

    @Resource
    private  ChapteDataRepository chapteDataRepository;
    @Resource
    private FileDatumRepository fileDatumRepository;
    @Resource
    private AudioDatumRepository audioDatumRepository;
    @Resource
    private LinkDatumRepository linkDatumRepository;
    @Resource
    private ViewDatumRepository viewDatumRepository;

    @Resource
    private PhotosRepository photosRepository;

    /**
     *
     * @param chapteDataReq
     * @return  int 添加的文件数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(ChapteDataReq chapteDataReq) {
        //1文档　2图册　3视频　4音频　5链接
        String datumType = chapteDataReq.getDatumType();
        List<ChapteData> chapteDataList = new ArrayList<>();
        switch (datumType){
            case "1":
//                List<FileDatum> fileDatumList = new ArrayList<>();
//                for (DataDatumVo dataDatumVo : chapteDataReq.getFiles()) {
//                    String uuid=StrUtil.uuid().replace("-","");
//                    FileDatum fd=new FileDatum();
//                    fd.setCourseId(chapteDataReq.getCourseId());
//                    fd.setChapterId(chapteDataReq.getChapterId());
//                    fd.setFileId(uuid);
//                    fd.setFileName(dataDatumVo.getFileName());
//                    fd.setFileType( FileUtils.ext(dataDatumVo.getFileName()));
//                    fd.setFileUrl(dataDatumVo.getFilePath());
//                    fileDatumList.add(fd);
//                }
//                List<FileDatum> list = fileDatumRepository.saveAll(fileDatumList);
//                list.forEach(fileDatum -> {
//                    chapteDataList.add(
//                    ChapteData.builder()
//                            .dataId(fileDatum.getFileId())
//                            .datumName(fileDatum.getFileName())
//                            .chapterId(chapteDataReq.getChapterId())
//                            .courseId(chapteDataReq.getCourseId())
//                            .datumArea(chapteDataReq.getDatumArea())
//                            .datumType(datumType)
//                            .build()
//                    );
//                });

                saveT(chapteDataReq,fileDatumRepository,new FileDatum());
                break;
            case "2":  //图册添加
//                String uuid=StrUtil.uuid().replace("-","");
//                PhotoSort photoSort = photoSortRepository.save(PhotoSort.builder()
//                        .sortImgId(uuid)
//                        .courseId(chapteDataReq.getCourseId())
//                        .sortImgType(1)
//                        .topPicSrc(chapteDataReq.getFiles().size() > 0 ? chapteDataReq.getFiles().get(0).getFilePath() : null)
//                        .sortImgName(chapteDataReq.getFiles().size() > 0 ? chapteDataReq.getPhotoName() : null)
//                        .build());
//                List<Photos> photosList = new ArrayList<>();
//                chapteDataReq.getFiles().forEach(file -> {
//                    photosList.add(
//                            Photos.builder()
//                                    .sortImgId(photoSort.getSortImgId())
//                                    .photoSrc(file.getFilePath())
//                                    .photoName(file.getFileName())
//                                    .build());
//                });
//
//                photosRepository.saveAll(photosList);
//                chapteDataList.add(ChapteData.builder()
//                        .dataId(photoSort.getSortImgId())
//                        .datumName(photoSort.getSortImgName())
//                        .chapterId(chapteDataReq.getChapterId())
//                        .courseId(chapteDataReq.getCourseId())
//                        .datumArea(chapteDataReq.getDatumArea())
//                        .datumType(datumType)
//                        .build());
                break;
            case "3":
                List<ViewDatum> viewDatas = new ArrayList<ViewDatum>();
//                chapteDataReq.getFiles().forEach(file -> {
//                    ViewDatum fd=new ViewDatum();
//                    String uuid=StrUtil.uuid().replace("-","");
//                    fd.setCourseId(chapteDataReq.getCourseId());
//                    fd.setChapterId(chapteDataReq.getChapterId());
//                    fd.setFileId(uuid);
//                    fd.setFileName(file.getFileName());
//                    fd.setFileType( FileUtils.ext(file.getFileName()));
//                    fd.setFileUrl(file.getFilePath());
//                    viewDatas.add(fd);
//                });
//                List<ViewDatum> viewDatumList = viewDatumRepository.saveAll(viewDatas);
//                viewDatumList.forEach(viewDatum -> {
//                    chapteDataList.add(ChapteData.builder()
//                                    .dataId(viewDatum.getFileId())
//                                    .datumName(viewDatum.getViewName())
//                                    .chapterId(chapteDataReq.getChapterId())
//                                    .courseId(chapteDataReq.getCourseId())
//                                    .datumArea(chapteDataReq.getDatumArea())
//                                    .datumType(datumType)
//                                    .build());
//                });
                break;
            case "4":
//                List<AudioDatum> audioDatas = new ArrayList<AudioDatum>();
//                chapteDataReq.getFiles().forEach(file -> {
//                    audioDatas.add(AudioDatum.builder()
//                            .audioName(file.getFileName())
//                            .audioType(FileUtils.ext(file.getFileName()))
//                            .audioUrl(file.getFilePath())
//                            .chapterId(chapteDataReq.getChapterId())
//                            .build());
//                });
//                List<AudioDatum> audioDatumList = audioDatumRepository.saveAll(audioDatas);
//                audioDatumList.forEach(audioData ->{
//                    chapteDataList.add(ChapteData.builder()
//                            .dataId(audioData.getAudioId())
//                            .datumName(audioData.getAudioName())
//                            .chapterId(chapteDataReq.getChapterId())
//                            .courseId(chapteDataReq.getCourseId())
//                            .datumArea(chapteDataReq.getDatumArea())
//                            .datumType(datumType)
//                            .build());
//                });
                break;
            case "5":
//                List<LinkDatum> linkDatumList = new ArrayList<LinkDatum>();
//                chapteDataReq.getFiles().forEach(link -> {
//                    linkDatumList.add(LinkDatum.builder()
//                            .chapterId(chapteDataReq.getChapterId())
//                            .linkName(link.getFileName())
//                            .linkUrl(link.getFilePath())
//                            .build());
//                });
//                List<LinkDatum> linkDatas = linkDatumRepository.saveAll(linkDatumList);
//                linkDatas.forEach(linkDatum -> {
//                    chapteDataList.add(ChapteData.builder()
//                            .dataId(linkDatum.getLinkId())
//                            .datumName(linkDatum.getLinkName())
//                            .chapterId(chapteDataReq.getChapterId())
//                            .courseId(chapteDataReq.getCourseId())
//                            .datumArea(chapteDataReq.getDatumArea())
//                            .datumType(datumType)
//                            .build());
//                });
                break;
//                default:
//                    MyAssert.isFalse(false,9999,"文件类型！");
        }
        return String.valueOf(chapteDataRepository.saveAll(chapteDataList).size());
    }

    public void saveT(ChapteDataReq chapteDataReq, IDatumRepoitory rep, AbsDatum fd){
        //1文档　2图册　3视频　4音频　5链接
        String datumType = chapteDataReq.getDatumType();
        List<ChapteData> chapteDataList = new ArrayList<>();
        switch (datumType) {
            case "1":
                List<AbsDatum> fileDatumList = new ArrayList<>();
                for (DataDatumVo dataDatumVo : chapteDataReq.getFiles()) {
                    String uuid = StrUtil.uuid().replace("-", "");
//                    FileDatum fd=new FileDatum();
                    fd.setCourseId(chapteDataReq.getCourseId());
                    fd.setChapterId(chapteDataReq.getChapterId());
                    fd.setFileId(uuid);
                    fd.setFileName(dataDatumVo.getFileName());
                    fd.setFileType(FileUtils.ext(dataDatumVo.getFileName()));
                    fd.setFileUrl(dataDatumVo.getFilePath());
                    fileDatumList.add(fd);
                }
                rep.saveAll(fileDatumList).forEach(fileDatum -> {
                    AbsDatum fd1=(AbsDatum)fileDatum;
                    ChapteData cd=new  ChapteData();
                    UpdateUtil.copyNullProperties(fd, cd);
                    cd.setDataId(fd.getFileId());
                    chapteDataList.add(cd);
                });

        }
    }

       /**
     * 多条件分页查询知识点
     * @param courseId
     * @param chapterId
     * @param kNodeId
     * @param pageable
     * @return
     */
    public Page<ChapteData> findDataPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
        return chapteDataRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<Predicate>();

            predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), TAKE_EFFECT_OPEN));

            if (StrUtil.isNotBlank(courseId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (StrUtil.isNotBlank(chapterId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("chapterId"), chapterId));
            }
            if (StrUtil.isNotBlank(kNodeId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("kNodeId"), kNodeId));
            }

            if (StrUtil.isNotBlank(datumArea)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("datumArea"), datumArea));
            }

            if (StrUtil.isNotBlank(datumType)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("datumType"), datumType));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);
    }

    public List<ChapteDataResp> findDataList(String courseId, String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable){
        Page<ChapteData> plist=findDataPage(courseId,chapterId,kNodeId,datumArea,datumType,pageable);
        return plist.getContent()
               .stream()
               .map((item)->{
                   return new ChapteDataResp(
                           item.getCourseId(),
                           item.getChapterId(),
                           item.getDatumArea(),
                           item.getDatumType(),
                           item.getDatumName(),
                           getFileSrc(item.getDatumType(),item.getDataId()));
        }).collect(toList());
    }


    private String getFileSrc(String datumType,String dataId){
        //资料类型 1文档　2图册　3视频　4音频　5链接

        String src="";
//        switch (datumType) {
//            case "1":
//                FileDatum file = fileDatumRepository.findById(dataId).get();
//                src = file.getFileUrl();
//                break;
//            case "2":
//                PhotoSort photo= photoSortRepository.findById(dataId).get();
//                src=photo.getTopPicSrc();
//                break;
//            case "3":
//                ViewDatum view = viewDatumRepository.findById(dataId).get();
//                src = view.getViewUrl();
//                break;
//            case "4":
//                AudioDatum audioDatum=audioDatumRepository.findById(dataId).get();
//                src=audioDatum.getAudioUrl();
//                break;
//            case "5":
//                LinkDatum linkDatum=linkDatumRepository.findById(dataId).get();
//                src=linkDatum.getLinkUrl();
//                break;
//        }
        return src;
    }

    @Override
    public int countJiaoan(String courseId) {
        return chapteDataRepository.countByCourseIdAndDatumAreaAndIsValidated(courseId,"1",TAKE_EFFECT_OPEN);
    }

    @Override
    public  List<IChapterDataCountDto> countKeJian(String courseId) {
        //ChapteDataResp
        return chapteDataRepository.countKeJian(courseId,"2",TAKE_EFFECT_OPEN);
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

}
