package com.forteach.education.service;

import com.forteach.education.domain.AudioDatum;
import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Page;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 20:53
 * @Version: 1.0
 * @Description: 音频资料库
 */
public interface AudioDatumService {

    AudioDatum save(AudioDatum audioDatum);

    AudioDatum edit(AudioDatum audioDatum);

    void delete(AudioDatum audioDatum);

    void deleteById(String audioId);

    void deleteIsValidById(String audioId);

    Page<AudioDatum> findAll(SortVo sortVo);

    AudioDatum getAudioDatumById(String audioId);
}
