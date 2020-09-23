package com.forteach.education.images.course.service;

import com.forteach.education.images.course.domain.ArticleImages;
import com.forteach.education.images.course.repository.ArticleImagesRepository;
import com.forteach.education.web.vo.DataDatumVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtIcleImagesService {

    @Resource
    private ArticleImagesRepository acrticleImagesRepository;

    @Transactional(rollbackForClassName = "Exception")
    public boolean saveImages(String articleId, List<DataDatumVo> dataList) {
        List<ArticleImages> list = new ArrayList<>();
//        List<DataDatumVo> dataDatumVos = dataList;
        for (int i = 0; i < dataList.size(); i++) {
            DataDatumVo dataDatumVo = dataList.get(i);
            list.add(ArticleImages.builder()
                    .articleId(articleId)
                    .indexNum(i + 1)
                    .imageName(dataDatumVo.getFileName())
                    .imageUrl(dataDatumVo.getFileUrl())
                    .build());
        }
        if (!list.isEmpty()) {
            acrticleImagesRepository.saveAll(list);
        }
        return true;
    }

    public List<ArticleImages> findImagesById(String articleId) {
        return acrticleImagesRepository.findByArticleIdOrderByIndexNumAsc(articleId);
    }
}
