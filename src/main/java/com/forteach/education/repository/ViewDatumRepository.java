package com.forteach.education.repository;

import com.forteach.education.domain.ViewDatum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:16
 * @Version: 1.0
 * @Description: 视频资料库
 */
public interface ViewDatumRepository extends JpaRepository<ViewDatum, String> {

}