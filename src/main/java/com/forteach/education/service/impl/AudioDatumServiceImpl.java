package com.forteach.education.service.impl;

import com.forteach.education.domain.AudioDatum;
import com.forteach.education.repository.AudioDatumRepository;
import com.forteach.education.service.AudioDatumService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 20:57
 * @Version: 1.0
 * @Description: 音频资料库
 */
@Slf4j
@Service
public class AudioDatumServiceImpl implements AudioDatumService {

    @Autowired
    private AudioDatumRepository audioDatumRepository;

    @Override
    public AudioDatum save(AudioDatum audioDatum) {
        return audioDatumRepository.save(audioDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AudioDatum edit(AudioDatum audioDatum) {
        return audioDatumRepository.save(audioDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(AudioDatum audioDatum) {
        audioDatumRepository.delete(audioDatum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String audioId) {
        audioDatumRepository.deleteById(audioId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String audioId) {
        AudioDatum audioDatum = audioDatumRepository.findById(audioId).get();
        audioDatum.setIsValidated(TAKE_EFFECT_CLOSE);
        audioDatumRepository.save(audioDatum);
    }

    @Override
    public Page<AudioDatum> findAll(SortVo sortVo) {
        return audioDatumRepository.findByIsValidatedEquals(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }

    @Override
    public AudioDatum getAudioDatumById(String audioId) {
        return audioDatumRepository.findById(audioId).get();
    }

    /**
     * 根据章节ID查询对应的音频信息
     * @param chapterId
     * @return
     */
    @Override
    public List<AudioDatum> findByChapterId(String chapterId) {
        return audioDatumRepository.findByIsValidatedEqualsAndChapterId(TAKE_EFFECT_OPEN, chapterId);
    }
}
