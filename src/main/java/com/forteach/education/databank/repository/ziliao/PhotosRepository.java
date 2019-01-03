package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.Photos;
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


}
