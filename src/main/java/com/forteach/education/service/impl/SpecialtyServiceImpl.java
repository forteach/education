package com.forteach.education.service.impl;

import com.forteach.education.domain.Specialty;
import com.forteach.education.repository.SpecialtyRepository;
import com.forteach.education.service.SpecialtyService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.StringUtil;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;

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

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Specialty edit(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty getSpecialtyById(String specialtyId) {
        return specialtyRepository.findById(specialtyId).get();
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
        Specialty specialty = specialtyRepository.findById(specialtyId).get();
        specialty.setIsValidated(TAKE_EFFECT_CLOSE);
        specialtyRepository.save(specialty);
    }

    /**
     * 分页查询科目信息
     * @param sortVo
     * @return
     */
    @Override
    public Page<Specialty> findAll(SortVo sortVo){
        Page<Specialty> page = specialtyRepository.findByIsValidatedEquals(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
        return page;
    }
}
