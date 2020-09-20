package com.forteach.education.statistics.repository;

import com.forteach.education.statistics.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/24 9:55
 * @Version: v1.0
 * @Modified：
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseRepositoryTest {
    @Autowired
    private CountCourseRepository countCourseRepository;
    @Autowired
    private CountFacultyRepository countFacultyRepository;
    @Autowired
    private CountLearnRepository countLearnRepository;
    @Autowired
    private CountScoreRepository countScoreRepository;
    @Autowired
    private CountTeachingRepository countTeachingRepository;
    @Autowired
    private CountViewsRepository countViewsRepository;

    @Test
    public void save(){
        CountCourse countCourse = new CountCourse();
        countCourse.setChapterNum(121);
        countCourse.setCourseId("2324");
        countCourse.setCourseName("电工电子");
        countCourse.setQuestionNum(233);
        countCourse.setTeacherNum(2);
        countCourse.setDataNum(1234);
        countCourseRepository.save(countCourse);

        CountFaculty countFaculty = new CountFaculty();
        countFaculty.setCourseViews(123);
        countFaculty.setDataNum(2344);
        countFaculty.setTeacherId("dll");
        countFaculty.setTeacherName("东力力");
        countFaculty.setTeachingOfficeId("2323");
        countFaculty.setTeachingOfficeName("数学教研室");
        countFacultyRepository.save(countFaculty);

        CountLearn countLearn = new CountLearn();
        countLearn.setClassId("12");
        countLearn.setClassName("班级1");
        countLearn.setClassRoomExerciseNum(123);
        countLearn.setHomeworkNum(23);
        countLearn.setStudentId("15010015");
        countLearn.setStudentName("张韩振");
        countLearn.setSpecialtyId("12");
        countLearn.setSpecialtyName("专业1");
        countLearn.setCourseNum(12);
        countLearn.setExperienceExchangeNum(32);
        countLearnRepository.save(countLearn);


        CountScore countScore = new CountScore();
        countScore.setClassId("13");
        countScore.setClassName("班级3");
        countScore.setCourseNum(11);
        countScore.setOutstandingNum(12);
        countScore.setPassNum(113);
        countScore.setStudentId("15010015");
        countScore.setStudentName("张韩振");
        countScore.setSpecialtyId("2323");
        countScore.setStudentName("专业1");
        countScore.setPassRate("0.33");
        countScore.setOutstandingRate("0.98");
        countScoreRepository.save(countScore);


        CountTeaching countTeaching = new CountTeaching();
        countTeaching.setChapterNum(312);
        countTeaching.setCourseNum(13);
        countTeaching.setDataNum(3456);
        countTeaching.setInteractionsNum(23);
        countTeaching.setQuestionNum(2323);
        countTeaching.setTeachingOfficeId("2323");
        countTeaching.setTeachingOfficeName("教研室");
        countTeaching.setTeacherId("dll");
        countTeaching.setTeacherName("东力力");
        countTeachingRepository.save(countTeaching);


        CountViews countViews = new CountViews();
        countViews.setTeacherId("bh");
        countViews.setTeacherName("卞慧");
        countViews.setCourseViewsNum(12);
        countViews.setDataViewsNum(323);
        countViews.setQuestionViewsNum(123);
        countViews.setTeachingOfficeId("057196");
        countViews.setTeachingOfficeName("财会教研室");
        countViewsRepository.save(countViews);
    }

}
