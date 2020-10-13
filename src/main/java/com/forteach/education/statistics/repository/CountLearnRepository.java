package com.forteach.education.statistics.repository;

import com.forteach.education.statistics.domain.CountLearn;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 20:37
 * @version: 1.0
 * @description：学习统计
 */
public interface CountLearnRepository extends BaseRepository<CountLearn, String> {

    /**
     * 查询专业对应的学习
     *
     * @param isValidated
     * @param specialtyId
     * @return
     */
    @Transactional(readOnly = true)
    List<CountLearn> findAllByIsValidatedEqualsAndSpecialtyId(String isValidated, String specialtyId);
}