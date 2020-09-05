package com.forteach.education.databank.service.imp;

import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.databank.domain.PlanDate;
import com.forteach.education.databank.repository.PlanDateRepository;
import com.forteach.education.databank.service.PlanDateService;
import com.forteach.education.databank.web.res.PlanDateRes;
import com.forteach.education.information.domain.MyArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 11:11
 * @Version: v1.0
 * @Modified：行程日程
 * @Description:
 */
@Service
@Slf4j
public class PlanDateServiceImpl implements PlanDateService {
    private final PlanDateRepository planDateRepository;

    public PlanDateServiceImpl(PlanDateRepository planDateRepository) {
        this.planDateRepository = planDateRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUpdate(PlanDate planDate) {
        planDateRepository.save(planDate);
    }

    @Override
    public List<PlanDateRes> findByOpenIdAndPlanDate(String openId, String contentDate) {
        return planDateRepository.findAllByOpenIdAndAndContentDateStartingWithOrderByContentDate(openId, contentDate)
                .stream().map(p ->
                        PlanDateRes.builder()
                                .id(p.getId())
                                .openId(p.getOpenId())
                                .content(p.getContent())
                                .contentDate(p.getContentDate())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        Optional<PlanDate> optional = planDateRepository.findById(id);
        MyAssert.isFalse(optional.isPresent(), DefineCode.ERR0013, "不存在对应的日程信息");
        planDateRepository.delete(optional.get());
    }
}
