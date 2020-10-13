package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.PlanDate;
import com.forteach.education.databank.web.res.PlanDateRes;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 11:10
 * @Version: v1.0
 * @Modified：日程行程
 * @Description:
 */
public interface PlanDateService {
    /**
     * @param planDate
     */
    void saveUpdate(PlanDate planDate);

    /**
     * @param openId
     * @param contentDate
     * @return
     */
    List<PlanDateRes> findByOpenIdAndPlanDate(String openId, String contentDate);

    /**
     * 删除日程
     *
     * @param id
     */
    void deleteById(String id);
}
