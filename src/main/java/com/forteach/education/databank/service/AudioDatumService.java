package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.AudioDatum;
import com.forteach.education.common.web.vo.SortVo;
import org.springframework.data.domain.Page;

import java.util.List;

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

    List<AudioDatum> findByChapterId(String chapterId);
}
