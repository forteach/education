package com.forteach.education.repository;

import com.forteach.education.domain.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:02
 * @Version: 1.0
 * @Description:　图片
 */
public interface PhotosRepository extends JpaRepository<Photos, String> {

    /**
     * 根据图册ID查询图册图片信息
     * @param sortImgId
     * @return
     */
    List<Photos> findBySortImgId(String sortImgId);

//    List<PhotoSortVo> findBySortImgId(String sortImgId);

    void deleteBySortImgId(String sortImgId);
}
