package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.PlanDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 11:11
 * @Version: v1.0
 * @Modified：行程
 * @Description:
 */
public interface PlanDateRepository extends JpaRepository<PlanDate, String> {
    /**
     * 查询微信用户的月份对应的日程计划
     * @param openId
     * @param contentDate
     * @return
     */
    @Transactional(readOnly = true)
    List<PlanDate> findAllByOpenIdAndAndContentDateStartingWithOrderByContentDate(String openId, String contentDate);
}
