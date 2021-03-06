package com.forteach.education.databank.service.imp;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.databank.domain.ziliao.*;
import com.forteach.education.databank.repository.ziliao.*;
import com.forteach.education.databank.service.ChapteDataService;
import com.forteach.education.databank.web.res.DatumResp;
import com.forteach.education.exception.AssertErrorException;
import com.forteach.education.util.FileUtils;
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
import java.util.Optional;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;
import static java.util.stream.Collectors.toList;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 11:04
 * @Version: 1.0
 * @Description: 课程资料操作
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
     * @param chapterId
     * @param datumArea
     * @param datumType
     * @param files
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(String courseId, String chapterId, String datumArea, String datumType, String teachShare, String stuShare, List<DataDatumVo> files) {
        //根据文件类型，对应保存信息
        //1文档　3视频　4音频　5链接
        String size = "";
        switch (datumType) {
            //文档
            case Dic.COURSE_ZILIAO_FILE:
                size = saveT(courseId, chapterId, datumArea, datumType, teachShare, stuShare, files, fileDatumRepository, new FileDatum());
                break;
            //视频
            case Dic.COURSE_ZILIAO_VIEW:
                size = saveT(courseId, chapterId, datumArea, datumType, teachShare, stuShare, files, viewDatumRepository, new ViewDatum());
                break;
            //音频
            case Dic.COURSE_ZILIAO_AUDIO:
                size = saveT(courseId, chapterId, datumArea, datumType, teachShare, stuShare, files, audioDatumRepository, new AudioDatum());
                break;
            //链接
            case Dic.COURSE_ZILIAO_LINK:
                size = saveT(courseId, chapterId, datumArea, datumType, teachShare, stuShare, files, linkDatumRepository, new LinkDatum());
                break;
            default:
                MyAssert.fail(DefineCode.ERR0010, new AssertErrorException(DefineCode.ERR0010, "文件类型不正确"), "文件类型不正确");
        }
        //添加成功后的文件数量
        return size;
    }

    /**
     * 单个资料领域修改
     *
     * @param fileId     资料类型
     * @param datumType  资料类型
     * @param datumArea  资料领域
     * @param teachShare 教师分享
     * @param stuShare   学生可见
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateAreaAndShare(String courseId, String chapterId, String kNodeId, String fileId, String datumType, String datumArea, String teachShare, String stuShare) {

        //1、根据资料编号和领域编号，获得领域表信息
        DatumArea da = datumAreaRepository.findByFileIdAndDatumArea(fileId, datumArea);

        //2、如果存在就删除，相反就添加
        if (da != null) {
            datumAreaRepository.deleteByFileIdAndDatumArea(fileId, datumArea);
        } else {
            da = new DatumArea();
            da.setFileId(IdUtil.fastSimpleUUID());
            da.setDatumArea(datumArea);
            da.setDatumType(datumType);
            da.setKNodeId(kNodeId);
            da.setChapterId(chapterId);
            da.setCourseId(courseId);
            datumAreaRepository.save(da);
        }

        //4、修改文件资料表的资料领域字段
        switch (datumType) {
            //文档
            case Dic.COURSE_ZILIAO_FILE:
                fileDatumRepository.updateDatumArea(fileId, datumArea);
                fileDatumRepository.updateStuShare(fileId, stuShare);
                fileDatumRepository.updateTeachShare(fileId, teachShare);
                break;
            //视频
            case Dic.COURSE_ZILIAO_VIEW:
                viewDatumRepository.updateDatumArea(fileId, datumArea);
                viewDatumRepository.updateStuShare(fileId, stuShare);
                viewDatumRepository.updateTeachShare(fileId, teachShare);
                break;
            //音频
            case Dic.COURSE_ZILIAO_AUDIO:
                audioDatumRepository.updateDatumArea(fileId, datumArea);
                audioDatumRepository.updateStuShare(fileId, stuShare);
                audioDatumRepository.updateTeachShare(fileId, teachShare);
                break;
            //链接
            case Dic.COURSE_ZILIAO_LINK:
                linkDatumRepository.updateDatumArea(fileId, datumArea);
                linkDatumRepository.updateStuShare(fileId, stuShare);
                linkDatumRepository.updateTeachShare(fileId, teachShare);
                break;
            default:
                MyAssert.fail(DefineCode.ERR0010, new AssertErrorException(DefineCode.ERR0010, "文件类型不正确"), "文件类型不正确");
        }
        return "ok";
    }

    /**
     * 根据资料领域、课程、章节、资料列表
     *
     * @param chapterId 章节编号
     * @param kNodeId   知识点编号
     * @param datumType 文件类型
     * @param pageable  分页对象
     * @return 资料文件列表
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumType, Pageable pageable) {
        //1、根据分拣类型，获得资料列表
        Page<? extends AbsDatum> plist = null;
        //文件
        if (datumType.equals(Dic.COURSE_ZILIAO_FILE)) {
            plist = findFileDatumPage(chapterId, kNodeId, datumType, pageable);
        }
        //音频
        if (datumType.equals(Dic.COURSE_ZILIAO_AUDIO)) {
            plist = findAudioDatumPage(chapterId, kNodeId, datumType, pageable);
        }
        //视频
        if (datumType.equals(Dic.COURSE_ZILIAO_VIEW)) {
            plist = findViewDatumPage(chapterId, kNodeId, datumType, pageable);
        }
        //链接
        if (datumType.equals(Dic.COURSE_ZILIAO_LINK)) {
            plist = findLinkDatumPage(chapterId, kNodeId, datumType, pageable);
        }

        //2、转换LIST对象
        return plist.getContent()
                .stream()
                .map((AbsDatum item) -> {
                    DatumResp dr = new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    return dr;
                }).collect(toList());
    }


    public Page<FileDatum> findFileDatumPage(String chapterId, String kNodeId, String datumType, Pageable pageable) {
        return fileDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            return setSpecification(root, criteriaQuery, criteriaBuilder, chapterId, kNodeId, datumType);
        }, pageable);
    }

    public Page<ViewDatum> findViewDatumPage(String chapterId, String kNodeId, String datumType, Pageable pageable) {
        return viewDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            return setSpecification(root, criteriaQuery, criteriaBuilder, chapterId, kNodeId, datumType);
        }, pageable);
    }

    public Page<AudioDatum> findAudioDatumPage(String chapterId, String kNodeId, String datumType, Pageable pageable) {
        return audioDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            return setSpecification(root, criteriaQuery, criteriaBuilder, chapterId, kNodeId, datumType);
        }, pageable);
    }

    public Page<LinkDatum> findLinkDatumPage(String chapterId, String kNodeId, String datumType, Pageable pageable) {
        return linkDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            return setSpecification(root, criteriaQuery, criteriaBuilder, chapterId, kNodeId, datumType);
        }, pageable);
    }

    /**
     * 按章节、知识点、资料类型动态查询数据
     *
     * @param root
     * @param criteriaQuery
     * @param criteriaBuilder
     * @param chapterId
     * @param kNodeId
     * @param datumType
     * @return
     */
    private Predicate setSpecification(Root<?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder, String chapterId, String kNodeId, String datumType) {
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
     * @param chapterId
     * @param kNodeId
     * @param datumArea 资料领域
     * @param datumType
     * @param pageable
     * @return
     */
    @Override
    public List<DatumResp> findDatumList(String chapterId, String kNodeId, String datumArea, String datumType, Pageable pageable) {
        //1、获得资料领域列表
        List<String> datumAreas = Arrays.asList(datumArea.split(","));
        List list = null;
        List<AbsDatum> fileList = null;
        //2、判断是否按知识点查询资料领域列表
        if (StrUtil.isBlank(kNodeId)) {
            list = datumAreaRepository.findByChapterIdAndDatumAreaIn(chapterId, datumAreas, pageable).getContent().stream().map(item -> item.getFileId()).collect(toList());
        } else {
            list = datumAreaRepository.findByChapterIdAndKNodeIdAndDatumAreaIn(chapterId, kNodeId, datumAreas, pageable).getContent().stream().map(item -> item.getFileId()).collect(toList());
        }

        //3、根据不同资料类型，获得资料列表数据
        if (datumType.equals(Dic.COURSE_ZILIAO_FILE)) {
            fileList = fileDatumRepository.findAllById(list);
        }

        if (datumType.equals(Dic.COURSE_ZILIAO_AUDIO)) {
            fileList = audioDatumRepository.findAllById(list);
        }

        if (datumType.equals(Dic.COURSE_ZILIAO_VIEW)) {
            fileList = viewDatumRepository.findAllById(list);
        }

        if (datumType.equals(Dic.COURSE_ZILIAO_LINK)) {
            fileList = linkDatumRepository.findAllById(list);
        }

        //4、转换LIST对象
        return fileList.stream()
                .map((AbsDatum item) -> {
                    DatumResp dr = new DatumResp();
                    UpdateUtil.copyNullProperties(item, dr);
                    return dr;
                }).collect(toList());
    }

    private String saveT(String courseId, String chapterId, String datumArea, String datumType, String teachShare, String stuShare, List<DataDatumVo> files, IDatumRepoitory rep, AbsDatum fd) {

        //1、添加资料文件列表明细
        List<AbsDatum> fileDatumList = new ArrayList<>();
        for (DataDatumVo dataDatumVo : files) {
            String uuid = IdUtil.fastSimpleUUID();
            fd.setChapterId(chapterId);
            fd.setFileId(uuid);
            fd.setFileName(dataDatumVo.getFileName());
            fd.setFileType(FileUtils.ext(dataDatumVo.getFileName()));
            fd.setFileUrl(dataDatumVo.getFileUrl());
            fd.setDatumType(datumType);
            fd.setStuShare(stuShare);
            fd.setTeachShare(teachShare);
            fd.setCourseId(courseId);
            fd.setDatumArea(datumArea);
            fileDatumList.add(fd);
        }
        rep.saveAll(fileDatumList);


        //2、添加文件所属领域信息--不经常频繁的添加资料
        fileDatumList.stream().forEach((absDatum) ->
        {
            final String id = absDatum.getFileId();
            final String type = absDatum.getDatumType();
            final String courseId1 = absDatum.getCourseId();
            final String chapterId1 = absDatum.getChapterId();
            final String knodeId = absDatum.getKNodeId();
            List<DatumArea> list = new ArrayList<DatumArea>();
            Arrays.stream(datumArea.split(",")).forEach((area) -> {
                DatumArea da = new DatumArea();
                da.setFileId(id);
                da.setDatumArea(area);
                da.setDatumType(type);
                da.setCourseId(courseId1);
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
    public void removeChapteDataList(String courseId, String chapterId, String datumType) {
        if (StrUtil.isBlank(datumType)) {
            //没有传需要删除类型需要全部删除
            //文档
            fileDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);
            //视频
            viewDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);
            //音频
            audioDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);
            //链接
            linkDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);

            //全部文件列表信息需要删除
            datumAreaRepository.deleteAllByChapterIdAndCourseId(chapterId, courseId);
        } else {
            //传值,有具体要删除的类型
            removeTypeDataList(courseId, chapterId, datumType);
        }
    }

    private void removeTypeDataList(String courseId, String chapterId, String datumType) {
        //删除文件信息列表
        datumAreaRepository.deleteAllByCourseIdAndChapterIdAndDatumType(courseId, chapterId, datumType);
        switch (datumType) {
            //文档
            case Dic.COURSE_ZILIAO_FILE:
                fileDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);
                break;
            //视频
            case Dic.COURSE_ZILIAO_VIEW:
                viewDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);
                break;
            //音频
            case Dic.COURSE_ZILIAO_AUDIO:
                audioDatumRepository.deleteAllByCourseIdAndChapterId(courseId, chapterId);
                break;
            //链接
            case Dic.COURSE_ZILIAO_LINK:
                linkDatumRepository.deleteAllByCourseIdAndChapterId(chapterId, courseId);
                break;
            default:
                MyAssert.fail(DefineCode.ERR0010, new AssertErrorException(DefineCode.ERR0010, "文件类型不正确"), "文件类型不正确");
        }
    }

    /**
     * 删除单个文件信息和列表
     *
     * @param fileId
     * @param datumArea
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeOne(String fileId, String datumArea) {
        DatumArea datum = datumAreaRepository.findByFileIdAndDatumArea(fileId, datumArea);
        MyAssert.isNull(datum, DefineCode.ERR0014, "不存在要删除的文件");
        String datumType = datum.getDatumType();
        //删除文件列表
        datumAreaRepository.deleteByFileIdAndDatumArea(fileId, datumArea);
        switch (datumType) {
            //文档
            case Dic.COURSE_ZILIAO_FILE:
                removeOne(fileId, datumArea, fileDatumRepository);
                break;
            //视频
            case Dic.COURSE_ZILIAO_VIEW:
                removeOne(fileId, datumArea, viewDatumRepository);
                break;
            //音频
            case Dic.COURSE_ZILIAO_AUDIO:
                removeOne(fileId, datumArea, audioDatumRepository);
                break;
            //链接
            case Dic.COURSE_ZILIAO_LINK:
                removeOne(fileId, datumArea, linkDatumRepository);
                break;
            default:
                MyAssert.fail(DefineCode.ERR0010, new AssertErrorException(DefineCode.ERR0010, "文件类型不正确"), "文件类型不正确");
        }
    }

    private void removeOne(String fileId, String datumArea, IDatumRepoitory rep) {
        Optional<AbsDatum> absDatumOptional = rep.findById(fileId);
        if (absDatumOptional.isPresent()) {
            AbsDatum datum = absDatumOptional.get();
            List<String> area = removeDatumArea(datum.getDatumArea(), datumArea);
            if (area.isEmpty()) {
                //删除领域类型,结果后领域为空，则没有相关类型，则可以删除文件详细信息
                rep.deleteById(fileId);
            } else {
                //删除移除相关类型后，需要修改其中的类型
                String dataAreas = StrUtil.join(",", area.stream().toArray(String[]::new));
                datum.setDatumArea(dataAreas);
                rep.save(datum);
            }
        }
    }

    /**
     * 从领域类型中将要删除的领域数据移除
     *
     * @param datumAreas
     * @param datumArea
     * @return
     */
    private List<String> removeDatumArea(String datumAreas, String datumArea) {
        List<String> datum = StrUtil.splitTrim(datumAreas, ",");
        List<String> datumList = new ArrayList<>();
        datum.forEach(d -> {
            if (!datumArea.equals(d)) {
                datumList.add(d);
            }
        });
        return datumList;
    }
}