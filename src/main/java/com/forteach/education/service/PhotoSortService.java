package com.forteach.education.service;

import com.forteach.education.domain.PhotoSort;
import com.forteach.education.web.vo.PhotoSortVo;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-27 08:57
 * @Version: 1.0
 * @Description: 操作图册信息
 */
public interface PhotoSortService {

    PhotoSort save(PhotoSortVo photoSortVo);

    PhotoSortVo findById(String sortImgId);

    void deleteById(String sortImgId);

    PhotoSort edit(PhotoSort photoSort);
}
