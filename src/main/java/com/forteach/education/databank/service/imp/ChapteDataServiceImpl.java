package com.forteach.education.databank.service.imp;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.course.domain.ziliao.CourseData;
import com.forteach.education.databank.domain.ziliao.*;
import com.forteach.education.databank.repository.ziliao.*;
import com.forteach.education.databank.service.ChapteDataService;
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
 * @Description:  课程资料操作
 */
@Service
@Slf4j
public class ChapteDataServiceImpl implements ChapteDataService {

    @Resource
    private FileDatumRepository fileDatumRepository;
    @Resource
    private AudioDatumRepository audioDatumRepository;
    @Resource
    private LinkDatumRepository linkDatumRepository;
    @Resource
    private ViewDatumRepository viewDatumRepository;
    @Resource
    private DatumAreaRepository datumAreaRepository;


    /**
     *
     * @param chapteDataReq  章节资源文件信息
     * @return  int 新增文件数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(ChapteDataReq chapteDataReq) {
        //根据文件类型，对应保存信息
        //1文档　3视频　4音频　5链接
        String datumType = chapteDataReq.getDatumType();
        String size="";
        switch (datumType){
            case Dic.COURSE_ZILIAO_FILE:  //文档
                size=saveT(chapteDataReq,fileDatumRepository,new FileDatum());
                break;
            case Dic.COURSE_ZILIAO_VIEW://视频
                size= saveT(chapteDataReq,viewDatumRepository,new ViewDatum());
                break;
            case Dic.COURSE_ZILIAO_AUDIO://音频
                size= saveT(chapteDataReq,audioDatumRepository,new AudioDatum());
                break;
            case Dic.COURSE_ZILIAO_LINK://链接
                size= saveT(chapteDataReq,linkDatumRepository,new LinkDatum());
                break;
        }
        //添加成功后的文件数量
        return size;
    }


    /**
     *   根据资料领域、课程、章节、资料列表
     * @param chapterId  章节编号
     * @param kNodeId    知识点编号
     * @param datumType  文件类型
     * @param pageable   分页对象
     * @return  资料文件列表
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable){
        //1、根据分拣类型，获得资料列表
        Page<? extends AbsDatum> plist=null;
        //文件
        if(datumType.equals(Dic.COURSE_ZILIAO_FILE)){
            plist=findFileDatumPage(chapterId,kNodeId,datumType,pageable);
        }
        //音频
        if(datumType.equals(Dic.COURSE_ZILIAO_AUDIO)){
            plist=findAudioDatumPage(chapterId,kNodeId,datumType,pageable);
        }
        //视频
        if(datumType.equals(Dic.COURSE_ZILIAO_VIEW)){
            plist=findViewDatumPage(chapterId,kNodeId,datumType,pageable);
        }
        //链接
        if(datumType.equals(Dic.COURSE_ZILIAO_LINK)){
            plist=findLinkDatumPage(chapterId,kNodeId,datumType,pageable);
        }

        //2、转换LIST对象
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

    public Page<ViewDatum> findViewDatumPage(String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return viewDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<AudioDatum> findAudioDatumPage(String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return audioDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    public Page<LinkDatum> findLinkDatumPage( String chapterId, String kNodeId,String datumType, Pageable pageable) {
        return linkDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) ->{return setSpecification(root,criteriaQuery,criteriaBuilder,chapterId,kNodeId,datumType);}, pageable);
    }

    /**
     * 按章节、知识点、资料类型动态查询数据
     * @param root
     * @param criteriaQuery
     * @param criteriaBuilder
     * @param chapterId
     * @param kNodeId
     * @param datumType
     * @return
     */
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

    //资料类型 1文档　　3视频　4音频　5链接
    if (StrUtil.isNotBlank(datumType)) {
        predicatesList.add(
                criteriaBuilder.equal(root.get("datumType"), datumType));
        }
    return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
    }

    /**
     *
     * @param chapterId
     * @param kNodeId
     * @param datumArea  资料领域
     * @param datumType
     * @param pageable
     * @return
     */

    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable ){
        //1、获得资料领域列表
        List<String>  datumAreas=Arrays.asList(datumArea.split(","));
        List list=null;
        List<AbsDatum> fileList=null;
        //2、判断是否按知识点查询资料领域列表
        if(StrUtil.isBlank(kNodeId)){
             list=datumAreaRepository.findByChapterIdAndDatumAreaIn(chapterId,datumAreas,pageable).getContent().stream().map(item->item.getFileId()).collect(toList());
        }else{
            list=datumAreaRepository.findByChapterIdAndKNodeIdAndDatumAreaIn(chapterId,kNodeId,datumAreas,pageable).getContent().stream().map(item->item.getFileId()).collect(toList());
        }

        //3、根据不同资料类型，获得资料列表数据
        if(datumType.equals(Dic.COURSE_ZILIAO_FILE)){
            fileList= fileDatumRepository.findAllById(list);
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

        //4、转换LIST对象
        return fileList.stream()
                .map((AbsDatum item)->{
                    DatumResp dr=new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    return  dr;
                }).collect(toList());
    }

    private String saveT(ChapteDataReq chapteDataReq, IDatumRepoitory rep, AbsDatum fd){

        String chapterId=chapteDataReq.getChapterId();
        String datumArea=chapteDataReq.getDatumArea();
        String datumType=chapteDataReq.getDatumType();

        //1、添加资料文件列表明细
        List<AbsDatum> fileDatumList = new ArrayList<>();
        for (DataDatumVo dataDatumVo : chapteDataReq.getFiles()) {
            String uuid = IdUtil.fastSimpleUUID();
            fd.setChapterId(chapterId);
            fd.setFileId(uuid);
            fd.setFileName(dataDatumVo.getFileName());
            fd.setFileType(FileUtils.ext(dataDatumVo.getFileName()));
            fd.setFileUrl(dataDatumVo.getFilePath());
            fd.setDatumType(datumType);
            fileDatumList.add(fd);
        }
        rep.saveAll(fileDatumList);


        //2、添加文件所属领域信息--不经常频繁的添加资料
        fileDatumList.stream().forEach((absDatum)->
        {
            final  String id= absDatum.getFileId();
            final  String type=absDatum.getDatumType();
            final  String  chapterId1=absDatum.getChapterId();
            final  String  knodeId=absDatum.getKNodeId();
            List<DatumArea>  list=new ArrayList<DatumArea>();
            Arrays.stream(datumArea.split(",")).forEach((area)->{
                DatumArea da=new DatumArea();
                da.setFileId(id);
                da.setDatumArea(area);
                da.setDatumType(type);
                da.setChapterId(chapterId1);
                da.setKNodeId(knodeId);

                list.add(da);
            });
            datumAreaRepository.saveAll(list);
        });
        //返回资料文件数量
        return String.valueOf(fileDatumList.size());
    }

    //***************************************************************************************8
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CourseData chapteData) {


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String dataId) {


    }

}
