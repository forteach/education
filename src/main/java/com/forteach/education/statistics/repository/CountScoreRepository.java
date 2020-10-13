package com.forteach.education.statistics.repository;

import com.forteach.education.statistics.domain.CountScore;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 20:36
 * @version: 1.0
 * @description：成绩统计表
 */
public interface CountScoreRepository extends BaseRepository<CountScore, String> {

    /**
     * 查询专业对应的成绩
     *
     * @param isValidated
     * @param specialtyId
     * @return
     */
    @Transactional(readOnly = true)
    List<CountScore> findAllByIsValidatedEqualsAndSpecialtyId(String isValidated, String specialtyId);
}