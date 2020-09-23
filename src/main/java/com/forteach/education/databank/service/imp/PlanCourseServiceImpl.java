package com.forteach.education.databank.service.imp;

import com.forteach.education.common.keyword.Dic;
import com.forteach.education.databank.domain.PlanCourse;
import com.forteach.education.databank.repository.PlanCourseRepository;
import com.forteach.education.databank.service.PlanCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 17:12
 * @Version: v1.0
 * @Modified：课程表计划
 * @Description:
 */
@Slf4j
@Service
public class PlanCourseServiceImpl implements PlanCourseService {
    private final PlanCourseRepository planCourseRepository;

    public PlanCourseServiceImpl(PlanCourseRepository planCourseRepository) {
        this.planCourseRepository = planCourseRepository;
    }

    @Override
    public List<PlanCourse> findMyPlanCourse(String year, String semester, String teacherId) {
        return planCourseRepository.findAllByIsValidatedEqualsAndYearAndSemesterAndTeacherId(Dic.TAKE_EFFECT_OPEN, year, semester, teacherId);
    }
}
