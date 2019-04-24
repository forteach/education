package com.forteach.education.images.course.service;

import com.forteach.education.images.course.domain.CourseImages;
import com.forteach.education.images.course.repository.CourseImagesRepository;
import com.forteach.education.web.vo.DataDatumVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

@Service
public class CourseImagesService {

    @Resource
    private CourseImagesRepository courseImagesRepository;

    @Transactional(rollbackForClassName = "Exception")
    public void saveCourseImages(String courseId, List<DataDatumVo> dataList) {
        List<CourseImages> list = new ArrayList<>();
        List<DataDatumVo> dataDatumVos = dataList;
        for (int i = 0; i < dataDatumVos.size(); i++) {
            DataDatumVo dataDatumVo = dataDatumVos.get(i);
            list.add(CourseImages.builder()
                    .courseId(courseId)
                    .indexNum(i + 1)
                    .imageName(dataDatumVo.getFileName())
                    .imageUrl(dataDatumVo.getFileUrl())
                    .build());
        }
        courseImagesRepository.saveAll(list);
    }

    public List<CourseImages> findImagesByCourseId(String courseId) {
        return courseImagesRepository.findByIsValidatedEqualsAndCourseIdOrderByIndexNumAsc(TAKE_EFFECT_OPEN, courseId);
    }
}
