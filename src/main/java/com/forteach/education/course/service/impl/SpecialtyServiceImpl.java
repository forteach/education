package com.forteach.education.course.service.impl;

import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.domain.Specialty;
import com.forteach.education.course.repository.SpecialtyRepository;
import com.forteach.education.course.service.SpecialtyService;
import com.forteach.education.util.StringUtil;
import com.forteach.education.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Optional;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 14:57
 * @Version: 1.0
 * @Description: 专业
 */
@Slf4j
@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Resource
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty save(String specialtyName) {
        Specialty specialty = specialtyRepository.findByIsValidatedEqualsAndSpecialtyName(TAKE_EFFECT_OPEN, specialtyName);
        if (specialty != null) {
            return specialtyRepository.save(Specialty.builder().specialtyName(specialtyName).build());
        }
        return specialty;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Specialty edit(Specialty specialty) {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialty.getSpecialtyId());
        if (optionalSpecialty.isPresent()){
            UpdateUtil.copyNullProperties(optionalSpecialty.get(), specialty);
            return specialtyRepository.save(specialty);
        }
        return null;
    }

    @Override
    public Specialty getSpecialtyById(String specialtyId) {
        return specialtyRepository.findById(specialtyId).orElseGet(Specialty::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String specialtyId) {
        specialtyRepository.deleteById(specialtyId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String specialtyId) {
        specialtyRepository.findById(specialtyId)
                .ifPresent(specialty -> {
                    specialty.setIsValidated(TAKE_EFFECT_CLOSE);
                    specialtyRepository.save(specialty);
                });
    }

    /**
     * 分页查询科目信息
     *
     * @param sortVo
     * @return
     */
    @Override
    public Page<Specialty> findAll(SortVo sortVo) {
        Page<Specialty> page = specialtyRepository.findByIsValidatedEqualsOrderByCreateTimeDesc(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize()));
        return page;
    }
}
