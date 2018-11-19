package com.forteach.education.repository;

import com.forteach.education.domain.AudioDatum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:52
 * @Version: 1.0
 * @Description: 音频资料库
 */
public interface AudioDatumRepository extends JpaRepository<AudioDatum, String> {
}
