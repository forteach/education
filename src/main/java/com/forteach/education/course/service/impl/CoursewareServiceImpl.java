package com.forteach.education.course.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class CoursewareServiceImpl implements CoursewareService {

    /**
     * 课程重要课件
     */
    @Resource
    private ImpCoursewareRepoitory impCoursewareRepoitory;

    /**
     * 图集信息
     */
    @Resource
    private CourseArlitsRepository courseArlitsRepository;

    /**
     * 图集图片操作
     */
    @Resource
    private PhotosRepository photoDatumRepository;


    /**
     * 保存除图集以外，重要课件文件
     *
     * @param obj
     * @return
     */
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
        }).collect(toList());

        if (!list.isEmpty()) {
            impCoursewareRepoitory.saveAll(list);
        }
        return getImpCourseware(obj.getChapterId(), obj.getImportantType(), obj.getDatumType());
    }

    /**
     * 保存图集
     *
     * @param obj
     * @return
     */
    @Override
    public List<CoursewareAll> saveCourseAtlit(ImpCoursewareAll obj) {

        CourseAtlits ca = new CourseAtlits();
        ca.setId(IdUtil.fastSimpleUUID());
        ca.setChapterId(obj.getChapterId());
        ca.setFileName(obj.getPhotoDatumName());
        //保存图集信息
        courseArlitsRepository.save(ca);

        List<Photos> list = obj.getFiles()
                .stream()
                .filter(Objects::nonNull)
                .map(item -> {
                    Photos photo = new Photos();
                    photo.setId(IdUtil.fastSimpleUUID());
                    photo.setArlitsId(ca.getId());
                    photo.setChapterId(obj.getChapterId());
                    UpdateUtil.copyNullProperties(item, photo);
                    return photo;
                }).collect(toList());

        if (!list.isEmpty()) {
            photoDatumRepository.saveAll(list);
        }
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
        }).collect(toList());

        if (!list.isEmpty()) {
            return new ImpCoursewareAll(chapterId, importantType, files.size(), datumType, "", files);
        }
        return null;
    }

    /**
     * 获得图集列表
     *
     * @param chapterId
     * @return
     */
    @Override
    public List<CoursewareAll> getCourseArlitsList(String chapterId) {
        return courseArlitsRepository.findByChapterIdAndIsValidated(chapterId, Dic.TAKE_EFFECT_OPEN)
                .stream()
                .filter(Objects::nonNull)
                .map(item -> {
                    CoursewareAll ca = new CoursewareAll();
                    UpdateUtil.copyNullProperties(item, ca);
                    return ca;
                }).collect(toList());
    }

    @Override
    public List<CoursewareAll> getPhotoList(String arlitId) {

        return photoDatumRepository.findByArlitsIdAndIsValidated(arlitId, Dic.TAKE_EFFECT_OPEN)
                .stream().filter(Objects::nonNull)
                .map(item -> {
                    CoursewareAll ca = new CoursewareAll();
                    UpdateUtil.copyNullProperties(item, ca);
                    return ca;
                }).collect(toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removePhotoList(String arlitId) {
        courseArlitsRepository.findById(arlitId).ifPresent(c -> {
            c.setIsValidated(TAKE_EFFECT_CLOSE);
            courseArlitsRepository.save(c);
        });
        List<Photos> photosList = photoDatumRepository.findByArlitsIdAndIsValidated(arlitId, Dic.TAKE_EFFECT_OPEN)
                .stream()
                .filter(Objects::nonNull)
                .peek(p -> p.setIsValidated(TAKE_EFFECT_CLOSE))
                .collect(toList());
        if (!photosList.isEmpty()) {
            photoDatumRepository.saveAll(photosList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseArlitsList(String chapterId) {
        List<CourseAtlits> courseAtlitsList = courseArlitsRepository.findByChapterIdAndIsValidated(chapterId, Dic.TAKE_EFFECT_OPEN).stream()
                .peek(c -> c.setIsValidated(TAKE_EFFECT_CLOSE))
                .collect(toList());
        if (!courseAtlitsList.isEmpty()) {
            courseArlitsRepository.saveAll(courseAtlitsList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseArlits(String fileId) {
        courseArlitsRepository.findById(fileId).ifPresent(c -> {
            c.setIsValidated(TAKE_EFFECT_CLOSE);
            courseArlitsRepository.save(c);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseware(String chapterId, String importantType, String datumType) {
        List<ImportantCourseware> list = impCoursewareRepoitory.findByChapterIdAndDatumTypeAndImportantTypeAndIsValidated(chapterId, datumType, importantType, Dic.TAKE_EFFECT_OPEN)
                .stream()
                .filter(Objects::nonNull)
                .peek(i -> i.setImportantType(TAKE_EFFECT_CLOSE))
                .collect(toList());
        if (!list.isEmpty()) {
            impCoursewareRepoitory.saveAll(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseAtlit(String chapterId) {
        removeCourseArlitsList(chapterId);
        List<Photos> photosList = photoDatumRepository.findByChapterId(chapterId).stream()
                .peek(p -> p.setIsValidated(TAKE_EFFECT_CLOSE))
                .collect(toList());
        if (!photosList.isEmpty()) {
            photoDatumRepository.saveAll(photosList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByFileId(String fileId) {
        impCoursewareRepoitory.deleteById(fileId);
    }
}

