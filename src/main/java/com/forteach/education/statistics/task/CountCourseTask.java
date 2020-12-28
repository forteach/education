package com.forteach.education.statistics.task;

import cn.hutool.core.bean.BeanUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.repository.CourseRepository;
import com.forteach.education.databank.repository.ziliao.DatumAreaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.forteach.education.common.keyword.Dic.COUNT_COURSE;
import static com.forteach.education.common.keyword.Dic.COUNT_COURSE_INFO;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/29 14:44
 * @Version: v1.0
 * @Modified：统计课程任务
 * @Description:
 */
@Slf4j
@Configuration
public class CountCourseTask {

    @Resource
    private CourseRepository courseRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private DatumAreaRepository datumAreaRepository;


    @Schedules({
            // TODO 注释每分钟执行任务
//            @Scheduled(cron = "0 0/30 * * * ?")
            @Scheduled(cron = "0 30 4 * * ?")
    })
    public void countCourse() {
        log.info("开始执行定时任务统计课程 ==> {}", LocalDateTime.now());
        if (log.isDebugEnabled()) {
            log.debug("执行线程 : {}", Thread.currentThread().getName());
        }
        Set<String> collect = courseRepository.findAllByIsValidated(Dic.TAKE_EFFECT_OPEN).stream()
                .map(Course::getCourseId)
                .peek(c -> {
                    stringRedisTemplate.opsForSet().add(COUNT_COURSE, c);
                    stringRedisTemplate.opsForHash().putAll(COUNT_COURSE_INFO.concat(c), BeanUtil.beanToMap(c));
                })
                .collect(Collectors.toSet());
        datumAreaRepository.findAllByIsValidatedGroupByCourseId()
                .forEach(n -> {
                    stringRedisTemplate.opsForHash().put(COUNT_COURSE_INFO.concat(n.getCourseId()), "dataNum", n.getDataNum());
                });
        //设置有效期7天
        stringRedisTemplate.expire(COUNT_COURSE, 7, TimeUnit.DAYS);
        collect.forEach(c -> stringRedisTemplate.expire(COUNT_COURSE_INFO.concat(c), 7, TimeUnit.DAYS));
        log.info("{}　<== 执行定时任务统计课程任务结束", LocalDateTime.now());
    }
}
