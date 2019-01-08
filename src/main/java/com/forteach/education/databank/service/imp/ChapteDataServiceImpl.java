package com.forteach.education.databank.service.imp;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.databank.domain.ziliao.*;
import com.forteach.education.databank.dto.IDatumAreaCountDto;
import com.forteach.education.databank.repository.ziliao.*;
import com.forteach.education.databank.service.ChapteDataService;
import com.forteach.education.databank.web.res.ChapteDataCountResp;
import com.forteach.education.databank.web.res.DatumResp;
import com.forteach.education.util.FileUtils;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.vo.DataDatumVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
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
    private PhotoDatumRepository photoDatumRepository;
    @Resource
    private DatumAreaRepository datumAreaRepository;


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
        String size="";
        switch (datumType){
            case Dic.COURSE_ZILIAO_FILE:
                size=saveT(chapteDataReq,fileDatumRepository,new FileDatum());
                break;
            case Dic.COURSE_ZILIAO_PHOTO:  //图册添加
                size= saveT(chapteDataReq,photoDatumRepository,new PhotoDatum());
                break;
            case Dic.COURSE_ZILIAO_VIEW:
                size= saveT(chapteDataReq,viewDatumRepository,new ViewDatum());
                break;
            case Dic.COURSE_ZILIAO_AUDIO:
                size= saveT(chapteDataReq,audioDatumRepository,new AudioDatum());
                break;
            case Dic.COURSE_ZILIAO_LINK:
                size= saveT(chapteDataReq,linkDatumRepository,new LinkDatum());
                break;
        }
        return size;
    }


    /**
     *   获得按资料领域、课程、章节、资料列表
     * @param courseId
     * @param chapterId
     * @param kNodeId
     * @param datumArea
     * @param datumType
     * @param pageable
     * @return
     */
    public List<DatumResp> findDatumList(String courseId, String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable){
        Page<? extends AbsDatum> plist=null;
        if(datumArea.equals(Dic.COURSE_ZILIAO_FILE)){
            plist=findFileDatumPage(courseId,chapterId,kNodeId,datumArea,datumType,pageable);
        }

        if(datumArea.equals(Dic.COURSE_ZILIAO_PHOTO)){
            plist=findPhotoDatumPage(courseId,chapterId,kNodeId,datumArea,datumType,pageable);
        }

        if(datumArea.equals(Dic.COURSE_ZILIAO_AUDIO)){
            plist=findAudioDatumPage(courseId,chapterId,kNodeId,datumArea,datumType,pageable);
        }

        if(datumArea.equals(Dic.COURSE_ZILIAO_VIEW)){
            plist=findViewDatumPage(courseId,chapterId,kNodeId,datumArea,datumType,pageable);
        }

        if(datumArea.equals(Dic.COURSE_ZILIAO_LINK)){
            plist=findLinkDatumPage(courseId,chapterId,kNodeId,datumArea,datumType,pageable);
        }

        //转换LIST对象
        return plist.getContent()
                .stream()
                .map((AbsDatum item)->{
                    DatumResp dr=new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    return  dr;
                }).collect(toList());
    }

    public Page<FileDatum> findFileDatumPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
        return fileDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,courseId,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<PhotoDatum> findPhotoDatumPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
        return photoDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,courseId,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<ViewDatum> findViewDatumPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
        return viewDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,courseId,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<AudioDatum> findAudioDatumPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
        return audioDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,courseId,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<LinkDatum> findLinkDatumPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
        return linkDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,courseId,chapterId,kNodeId,datumType);}, pageable);
    }

private Predicate setSpecification(Root<?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder,String courseId,String chapterId,String kNodeId,String datumType){
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

//    if (StrUtil.isNotBlank(datumArea)) {
//        predicatesList.add(
//                criteriaBuilder.equal(root.get("datumArea"), datumArea));
//    }

    //资料类型 1文档　2图册　3视频　4音频　5链接
    if (StrUtil.isNotBlank(datumType)) {
        predicatesList.add(
                criteriaBuilder.equal(root.get("datumType"), datumType));
    }
    return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
}

    @Override
    public ChapteDataCountResp countJiaoAn(String courseId, String chapterId) {

         ChapteDataCountResp cd=new ChapteDataCountResp();
         cd.setDatumArea("1");
         cd.setDatumType("0");
         cd.setDCount(datumAreaRepository.countByCourseIdAndChapterIdAndDatumAreaAndIsValidated(courseId,chapterId,"1",TAKE_EFFECT_OPEN));
         return cd;
    }

    @Override
    public  List<IDatumAreaCountDto> countKeJian(String courseId,String chapterId) {
        //ChapteDataResp
        return datumAreaRepository.countKeJian(courseId,chapterId,"2",TAKE_EFFECT_OPEN);
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


    private String saveT(ChapteDataReq chapteDataReq, IDatumRepoitory rep, AbsDatum fd){
        //添加资料文件关联记录
        String dataId = StrUtil.uuid().replace("-", "");
        String chapterid=chapteDataReq.getChapterId();
        String courseid=chapteDataReq.getCourseId();
        String datumArea=chapteDataReq.getDatumArea();
        String datumName=chapteDataReq.getDatumName();
        String datumType=chapteDataReq.getDatumType();
        String kNodeId=chapteDataReq.getKNodeId();

        ChapteData cd=new  ChapteData();
        cd.setDataId(dataId);
        cd.setChapterId(chapterid);
        cd.setCourseId(courseid);
        cd.setDatumArea(datumArea);
        cd.setDatumType(datumType);
        cd.setDatumName(datumName);
        cd.setKNodeId(kNodeId);

        chapteDataRepository.save(cd);

        //添加资料文件列表明细
        List<AbsDatum> fileDatumList = new ArrayList<>();
        for (DataDatumVo dataDatumVo : chapteDataReq.getFiles()) {
            String uuid = StrUtil.uuid().replace("-", "");
            fd.setCourseId(courseid);
            fd.setChapterId(chapterid);
            fd.setFileId(uuid);
            fd.setDataId(dataId);
            fd.setFileName(dataDatumVo.getFileName());
            fd.setFileType(FileUtils.ext(dataDatumVo.getFileName()));
            fd.setFileUrl(dataDatumVo.getFilePath());
            fd.setDatumType(datumType);
            fileDatumList.add(fd);
        }
        rep.saveAll(fileDatumList);


        //添加文件所属领域信息--不经常频繁的添加资料
        fileDatumList.stream().forEach((absDatum)->
        {
            final  String id= absDatum.getFileId();
            final  String type=absDatum.getDatumType();
            Arrays.stream(datumArea.split(",")).forEach((area)->{
                DatumArea da=new DatumArea();
                da.setFileId(id);
                da.setDatumArea(area);
                da.setDatumType(type);
                datumAreaRepository.save(da);
            });
        });

        return String.valueOf(fileDatumList.size());
    }

//       /**
//     * 多条件分页查询知识点
//     * @param courseId
//     * @param chapterId
//     * @param kNodeId
//     * @param pageable
//     * @return
//     */
//    public Page<ChapteData> findDataPage(String courseId, String chapterId, String kNodeId,String datumArea,String datumType, Pageable pageable) {
//        return chapteDataRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {return setSpecification(root,criteriaQuery,criteriaBuilder,courseId,chapterId,kNodeId,datumArea,datumType);}, pageable);
//    }
}
