package com.forteach.education.course.repository.ziliao;

import com.forteach.education.course.domain.ziliao.Photos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:02
 * @Version: 1.0
 * @Description:　图片
 */
public interface PhotosRepository extends JpaRepository<Photos, String> {

    @Transactional(readOnly = true)
    public List<Photos> findByArlitsIdAndIsValidated(String arlitsId, String isValidated);

    public Page<Photos> findByArlitsIdAndIsValidated(String arlitsId, String isValidated, Pageable pageable);
}
