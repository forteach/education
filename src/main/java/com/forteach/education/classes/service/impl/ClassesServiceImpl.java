package com.forteach.education.classes.service.impl;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.classes.domain.Classes;
import com.forteach.education.classes.repository.ClassesRepository;
import com.forteach.education.classes.service.ClassesService;
import com.forteach.education.classes.web.req.ClassesVo;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-11 11:06
 * @version: 1.0
 * @description:
 */
@Slf4j
@Service
public class ClassesServiceImpl implements ClassesService {

    private final ClassesRepository classesRepository;

    public ClassesServiceImpl(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }


    @Override
    @Cacheable(value = "findAll", key = "#root.targetClass", unless = "#result eq null")
    public List<Classes> findAll() {
        List<Classes> list = classesRepository.findByIsValidatedEqualsOrderByCreateTimeDesc(TAKE_EFFECT_OPEN);
        list.parallelStream()
                .forEach(classes -> {
                    classes.setUpdateTime(null);
                    classes.setIsValidated(null);
                    classes.setCreateUser(null);
                    classes.setCreateTime(null);
                    classes.setUpdateUser(null);
                });
        return list;
    }

    @Override
    public Page<Classes> pageAll(SortVo sortVo) {
        return classesRepository.findByIsValidatedEqualsOrderByCreateTimeDesc(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "findAll", key = "#root.targetClass", beforeInvocation = true)
    public Classes editClass(ClassesVo classesVo) {
        Optional<Classes> classesOptional = classesRepository.findById(classesVo.getClassId());
        if (classesOptional.isPresent()) {
            Classes classes = classesOptional.get();
            if (StrUtil.isNotBlank(classesVo.getClassName())) {
                classes.setClassName(classesVo.getClassName());
            }
            if (StrUtil.isNotBlank(String.valueOf(classesVo.getGrade()))) {
                classes.setGrade(classesVo.getGrade().longValue());
            }
            return classesRepository.saveAndFlush(classes);
        } else {
            return null;
        }
    }
}