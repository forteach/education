package com.forteach.education.databank.service.imp;

import cn.hutool.core.util.IdUtil;
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
     * @param chapterId
     * @param kNodeId
     * @param datumType
     * @param pageable
     * @return
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable){
        Page<? extends AbsDatum> plist=null;
        if(datumType.equals(Dic.COURSE_ZILIAO_FILE)){
            plist=findFileDatumPage(chapterId,kNodeId,datumType,pageable);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_PHOTO)){
            plist=findPhotoDatumPage(chapterId,kNodeId,datumType,pageable);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_AUDIO)){
            plist=findAudioDatumPage(chapterId,kNodeId,datumType,pageable);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_VIEW)){
            plist=findViewDatumPage(chapterId,kNodeId,datumType,pageable);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_LINK)){
            plist=findLinkDatumPage(chapterId,kNodeId,datumType,pageable);
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

    public Page<FileDatum> findFileDatumPage(String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return fileDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<PhotoDatum> findPhotoDatumPage(String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return photoDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<ViewDatum> findViewDatumPage(String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return viewDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<AudioDatum> findAudioDatumPage(String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return audioDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<LinkDatum> findLinkDatumPage( String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return linkDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

   private Predicate setSpecification(Root<?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder,String chapterId,String kNodeId,String datumType){
    List<Predicate> predicatesList = new ArrayList<Predicate>();

    predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), TAKE_EFFECT_OPEN));

    if (StrUtil.isNotBlank(chapterId)) {
        predicatesList.add(
                criteriaBuilder.equal(root.get("chapterId"), chapterId));
    }
    if (StrUtil.isNotBlank(kNodeId)) {
        predicatesList.add(
                criteriaBuilder.equal(root.get("kNodeId"), kNodeId));
    }

    //资料类型 1文档　2图册　3视频　4音频　5链接
    if (StrUtil.isNotBlank(datumType)) {
        predicatesList.add(
                criteriaBuilder.equal(root.get("datumType"), datumType));
        }
    return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
    }

    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable ){
        List<String>  datumTypes=Arrays.asList(datumArea.split(","));
        List list=null;
        List<AbsDatum> fileList=null;
        if(StrUtil.isBlank(kNodeId)){
             list=datumAreaRepository.findByChapterIdAndDatumAreaIn(chapterId,datumTypes,pageable).getContent().stream().map(item->item.getFileId()).collect(toList());
        }else{
            list=datumAreaRepository.findByChapterIdAndKNodeIdAndDatumAreaIn(chapterId,kNodeId,datumTypes,pageable).getContent().stream().map(item->item.getFileId()).collect(toList());
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_FILE)){
            fileList= fileDatumRepository.findAllById(list);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_PHOTO)){
            fileList= photoDatumRepository.findAllById(list);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_AUDIO)){
            fileList= audioDatumRepository.findAllById(list);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_VIEW)){
            fileList= viewDatumRepository.findAllById(list);
        }

        if(datumType.equals(Dic.COURSE_ZILIAO_LINK)){
            fileList= linkDatumRepository.findAllById(list);
        }

        //转换LIST对象
        return fileList.stream()
                .map((AbsDatum item)->{
                    DatumResp dr=new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    return  dr;
                }).collect(toList());
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
        String dataId = IdUtil.fastSimpleUUID();
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
            String uuid = IdUtil.fastSimpleUUID();
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
            final  String  chapterId=absDatum.getChapterId();
            final  String  knodeId=absDatum.getKNodeId();
            List<DatumArea>  list=new ArrayList<DatumArea>();
            Arrays.stream(datumArea.split(",")).forEach((area)->{
                DatumArea da=new DatumArea();
                da.setFileId(id);
                da.setDatumArea(area);
                da.setDatumType(type);
                da.setChapterId(chapterId);
                da.setKNodeId(knodeId);
                list.add(da);
                //datumAreaRepository.save(da);
            });
            datumAreaRepository.saveAll(list);
            log.info("-------{}",list.size());
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
