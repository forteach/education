package com.forteach.education.service.impl;

import com.forteach.education.domain.PhotoSort;
import com.forteach.education.domain.Photos;
import com.forteach.education.repository.PhotoSortRepository;
import com.forteach.education.repository.PhotosRepository;
import com.forteach.education.service.PhotoSortService;
import com.forteach.education.util.UpdateTool;
import com.forteach.education.web.vo.PhotoSortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-27 08:59
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class PhotoSortServiceImpl implements PhotoSortService {

    @Autowired
    private PhotoSortRepository photoSortRepository;

    @Autowired
    private PhotosRepository photosRepository;

    /**
     * 保存图册信息
     * @param photoSortVo
     * @return PhotoSort
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PhotoSort save(PhotoSortVo photoSortVo) {
        PhotoSort photoSort = photoSortRepository.saveAndFlush(PhotoSort.builder()
                .courseId(photoSortVo.getCourceId())
                .imgPassword(photoSortVo.getImgPassword())
                .sortImgName(photoSortVo.getSortImgName())
                .topPicSrc(photoSortVo.getTopPicSrc())
                .sortImgType(photoSortVo.getSortImgType())
                .build());
        photoSortVo.getPhotos().forEach(photo -> {
            photo.setSortImgId(photoSort.getSortImgId());
        });
        photosRepository.saveAll(photoSortVo.getPhotos());
        return photoSort;
    }

    /**
     * 根据图册ID查询图册详情信息
     * @param photoId
     * @return
     */
    public PhotoSortVo findById(String photoId){
        PhotoSort photoSort = photoSortRepository.findById(photoId).get();
        List<Photos> photosList = photosRepository.findBySortImgId(photoSort.getSortImgId());
        PhotoSortVo photoSortVo = new PhotoSortVo();
        BeanUtils.copyProperties(photoSort, photoSortVo);
        photoSortVo.setPhotos(photosList);
        return photoSortVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public void deleteById(String sortImgId) {
        photosRepository.deleteBySortImgId(sortImgId);
        photoSortRepository.deleteById(sortImgId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public PhotoSort edit(PhotoSort photoSort){
        PhotoSort source =  photoSortRepository.findById(photoSort.getSortImgId()).get();
        UpdateTool.copyNullProperties(source, photoSort);
        return photoSortRepository.save(photoSort);
    }
}
