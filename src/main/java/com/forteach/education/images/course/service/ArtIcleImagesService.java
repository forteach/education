package com.forteach.education.images.course.service;

import com.forteach.education.images.course.domain.ArticleImages;
import com.forteach.education.images.course.domain.CourseImages;
import com.forteach.education.images.course.repository.ArticleImagesRepository;
import com.forteach.education.images.course.repository.CourseImagesRepository;
import com.forteach.education.web.vo.DataDatumVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

@Service
public class ArtIcleImagesService {

    @Resource
    private ArticleImagesRepository acrticleImagesRepository;

    @Transactional(rollbackForClassName = "Exception")
    public boolean saveImages(String articleId, List<DataDatumVo> dataList) {
        List<ArticleImages> list = new ArrayList<>();
        List<DataDatumVo> dataDatumVos = dataList;
        for (int i = 0; i < dataDatumVos.size(); i++) {
            DataDatumVo dataDatumVo = dataDatumVos.get(i);
            list.add(ArticleImages.builder()
                    .articleId(articleId)
                    .indexNum(i + 1)
                    .imageName(dataDatumVo.getFileName())
                    .imageUrl(dataDatumVo.getFileUrl())
                    .build());
        }
        acrticleImagesRepository.saveAll(list);
        return true;
    }

    public List<ArticleImages> findImagesById(String articleId) {
        return acrticleImagesRepository.findByArticleIdOrderByIndexNumAsc(articleId);
    }
}
