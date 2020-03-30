package com.forteach.education.service.impl;

import com.forteach.education.course.domain.CourseChapter;
import com.forteach.education.course.dto.ICourseChapterDto;
import com.forteach.education.course.repository.CourseChapterRepository;
import com.forteach.education.course.service.CourseChapterService;
import com.forteach.education.web.resp.CourseTreeResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.forteach.education.common.keyword.Dic.COURSE_CHAPTER_CHAPTER_PARENT_ID;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 13:31
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseChapterServiceImplTest {

    @Autowired
    private CourseChapterService courseChapterService;

    @Qualifier("courseChapterRepository")
    @Autowired
    private CourseChapterRepository courseChapterRepository;

    @Test
    public void findByCourseId() {
        List<CourseTreeResp> list = courseChapterService.findByCourseId("ff808181673e5e6c01673e5f792b0001");
        list.forEach(courseTreeResp -> {

            log.info("courseTreeResp : {}", courseTreeResp.toString());
        });
    }

    @Test
    public void findByChapterParentId() {
        List<ICourseChapterDto> list = courseChapterService.findByChapterParentId("0", "2c91808d678e620701679c214f500001");
        list.forEach(dto -> {
            log.info("ICourseChapterDto : {}", dto.getChapterName());
        });
    }

    @Test
    public void deleteById() {
        courseChapterService.deleteById("2c91809267462308016746293a2a0002");
    }

    @Test
    public void findLists() {
        Set<String> set = findLists("ff808181673e5e6c01673e5f792b0001", "ff808181673e9f6801673ea011950000");
        set.stream().forEach(System.out::println);
    }

    private Set<String> findLists(String courseId, String chapterParentId) {
        List<CourseChapter> lists = courseChapterRepository.findByCourseIdAndAndChapterParentId(courseId, chapterParentId);
        Set<String> stringSet = lists.stream().filter(courseChapter -> !COURSE_CHAPTER_CHAPTER_PARENT_ID.equals(courseChapter.getChapterParentId()))
                .map(CourseChapter::getChapterId)
                .collect(Collectors.toSet());
        stringSet.stream().forEach(s -> {
            //查询对应的目录集合
            log.info("s : {} ", s);
            findLists(courseId, s);
        });
        return stringSet;
    }

    @Test
    public void deleteById1() {
        courseChapterService.deleteById("ff808181673e9f6801673ea011950000");
    }

    @Test
    public void deleteIsValidById() {
        courseChapterService.deleteIsValidById("ff80818167a58ad30167a58e69d50006");
    }
}