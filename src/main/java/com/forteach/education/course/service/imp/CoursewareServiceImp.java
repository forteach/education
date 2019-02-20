package com.forteach.education.course.service.imp;

import cn.hutool.core.util.IdUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.course.domain.ziliao.CourseAtlits;
import com.forteach.education.course.domain.ziliao.ImportantCourseware;
import com.forteach.education.course.domain.ziliao.Photos;
import com.forteach.education.course.repository.ziliao.CourseArlitsRepository;
import com.forteach.education.course.repository.ziliao.ImpCoursewareRepoitory;
import com.forteach.education.course.repository.ziliao.PhotosRepository;
import com.forteach.education.course.service.CoursewareService;
import com.forteach.education.course.web.req.CoursewareAll;
import com.forteach.education.course.web.req.ImpCoursewareAll;
import com.forteach.education.util.FileUtils;
import com.forteach.education.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CoursewareServiceImp implements CoursewareService {

    //课程重要课件
    @Resource
    private ImpCoursewareRepoitory impCoursewareRepoitory;

    //图集信息
    @Resource
    private CourseArlitsRepository courseArlitsRepository;

    //图集图片操作
    @Resource
    private PhotosRepository photoDatumRepository;


    //保存除图集以外，重要课件文件
    @Override
    public ImpCoursewareAll saveFile(ImpCoursewareAll obj) {

        List<ImportantCourseware> list = obj.getFiles().stream().map(item -> {
            ImportantCourseware ic = new ImportantCourseware();
            ic.setId(IdUtil.fastSimpleUUID());
            UpdateUtil.copyNullProperties(item, ic);
            ic.setImportantType(obj.getImportantType());
            ic.setFileType((FileUtils.ext(ic.getFileName())));
            ic.setChapterId(obj.getChapterId());
            ic.setDatumType(obj.getDatumType());
            ic.setImportantType(obj.getImportantType());
            return ic;
        }).collect(Collectors.toList());

        impCoursewareRepoitory.saveAll(list);
        return getImpCourseware(obj.getChapterId(), obj.getImportantType(), obj.getDatumType());
    }

    //保存图集
    @Override
    public List<CoursewareAll> saveCourseAtlit(ImpCoursewareAll obj) {

        CourseAtlits ca = new CourseAtlits();
        ca.setId(IdUtil.fastSimpleUUID());
        ca.setChapterId(obj.getChapterId());
        ca.setFileName(obj.getPhotoDatumName());
        //保存图集信息
        courseArlitsRepository.save(ca);

        List<Photos> list = obj.getFiles().stream().map(item -> {
            Photos photo = new Photos();
            photo.setId(IdUtil.fastSimpleUUID());
            photo.setArlitsId(ca.getId());
            photo.setChapterId(obj.getChapterId());
            UpdateUtil.copyNullProperties(item, photo);
            return photo;
        }).collect(Collectors.toList());

        photoDatumRepository.saveAll(list);
        return getCourseArlitsList(obj.getChapterId());

    }

    /**
     * 获得重要除图集以外，课件文件列表
     *
     * @param chapterId
     * @param importantType
     * @param datumType
     * @return
     */
    @Override
    public ImpCoursewareAll getImpCourseware(String chapterId, String importantType, String datumType) {
        List<ImportantCourseware> list = impCoursewareRepoitory.findByChapterIdAndDatumTypeAndImportantTypeAndIsValidated(chapterId, datumType, importantType, Dic.TAKE_EFFECT_OPEN);
        List<CoursewareAll> files = list.stream().map((ImportantCourseware item) -> {
            CoursewareAll ca = new CoursewareAll();
            UpdateUtil.copyNullProperties(item, ca);
            return ca;
        }).collect(Collectors.toList());

        if (list.size() > 0) {
            return new ImpCoursewareAll(chapterId, importantType, files.size(), datumType, "", files);
        }
        return null;
    }

    //获得图集列表
    @Override
    public List<CoursewareAll> getCourseArlitsList(String chapterId) {

        List<CoursewareAll> list = courseArlitsRepository.findByChapterIdAndIsValidated(chapterId, Dic.TAKE_EFFECT_OPEN)
                .stream().map((item) -> {
                            CoursewareAll ca = new CoursewareAll();
                            UpdateUtil.copyNullProperties(item, ca);
                            return ca;
                        }
                ).collect(Collectors.toList());

        return list;

    }

    @Override
    public List<CoursewareAll> getPhotoList(String arlitId) {

        List<CoursewareAll> phlist = photoDatumRepository.findByArlitsIdAndIsValidated(arlitId, Dic.TAKE_EFFECT_OPEN)
                .stream().map((item) -> {
                    CoursewareAll ca = new CoursewareAll();
                    UpdateUtil.copyNullProperties(item, ca);
                    return ca;
                }).collect(Collectors.toList());
        return phlist;
    }

}

