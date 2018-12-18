package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
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

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteBySortImgId(String sortImgId);
}
