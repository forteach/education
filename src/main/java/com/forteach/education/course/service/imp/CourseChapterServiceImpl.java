package com.forteach.education.course.service.imp;

import com.forteach.education.course.domain.CourseChapter;
import com.forteach.education.course.dto.ICourseChapterDto;
import com.forteach.education.course.repository.CourseChapterRepository;
import com.forteach.education.course.service.CourseChapterService;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseChapterEditReq;
import com.forteach.education.web.resp.CourseTreeResp;
import com.forteach.education.web.resp.State;
import com.forteach.education.web.vo.CourseChapterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.forteach.education.common.keyword.Dic.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 17:14
 * @Version: 1.0
 * @Description: 科目章节管理
 */
@Slf4j
@Service
public class CourseChapterServiceImpl implements CourseChapterService {


    @Resource
    private CourseChapterRepository courseChapterRepository;

    @Override
    @Transactional(rollbackForClassName="Exception")
    public CourseChapter save(CourseChapter courseChapter) {
        //判断是顶层章节
        if (COURSE_CHAPTER_CHAPTER_PARENT_ID.equals(courseChapter.getChapterParentId())){
            courseChapter.setChapterLevel(1);

        }else {
            CourseChapter c = courseChapterRepository.findById(courseChapter.getChapterParentId()).get();
            courseChapter.setChapterLevel(c.getChapterLevel() + 1);
        }
        //查询当前科目章节有多少条数据
        long count = courseChapterRepository.countByIsValidatedEqualsAndCourseIdAndChapterParentId(TAKE_EFFECT_OPEN,
                courseChapter.getCourseId(), courseChapter.getChapterParentId());
        courseChapter.setSort(Integer.valueOf(String.valueOf(count + 1)));
        return courseChapterRepository.save(courseChapter);
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
    public CourseChapter edit(CourseChapterEditReq courseChapterEditReq) {
        CourseChapter source = courseChapterRepository.findById(courseChapterEditReq.getChapterId()).get();
        CourseChapter courseChapter = CourseChapter.builder().build();
        BeanUtils.copyProperties(courseChapterEditReq, courseChapter);
        UpdateUtil.copyNullProperties(source, courseChapter);
        return courseChapterRepository.save(courseChapter);
    }

    @Override
    public CourseChapter getCourseChapterById(String chapterId) {
        return courseChapterRepository.findById(chapterId).get();
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
    public void delete(CourseChapter courseChapter) {
        courseChapterRepository.delete(courseChapter);
    }

    /**
     * 删除树状结构科目课程章节及子章节
     * @param chapterId
     */
    @Override
    @Transactional(rollbackForClassName="Exception")
    public void deleteById(String chapterId) {
        CourseChapter courseChapter = courseChapterRepository.findById(chapterId).get();
        Set<String> stringSet = findLists(courseChapter.getCourseId(), chapterId);
        stringSet.add(chapterId);
        int result = courseChapterRepository.deleteBathIds(stringSet);
        log.info("chapterId : {}, deleteBath : {}", result);
    }

    /**
     * 根据父节点查询子章节的ID信息
     * @param courseId
     * @param ChapterParentId
     * @return
     */
    private Set<String> findLists(String courseId, String ChapterParentId){
        List<CourseChapter> lists = courseChapterRepository.findByCourseIdAndAndChapterParentId(courseId, ChapterParentId);
        Set<String> stringSet = lists.stream().filter(courseChapter -> !COURSE_CHAPTER_CHAPTER_PARENT_ID.equals(courseChapter.getChapterParentId()))
                .map(CourseChapter::getChapterId)
                .collect(Collectors.toSet());
        stringSet.parallelStream().map(s -> {
            //查询对应的目录集合
            return findLists(s, courseId);
        });
        return stringSet;
    }

    @Override
    @Transactional(rollbackForClassName="Exception")
    public void deleteIsValidById(String chapterId) {
        CourseChapter courseChapter = courseChapterRepository.findById(chapterId).get();
        Set<String> stringSet = findLists(courseChapter.getCourseId(), chapterId);
        stringSet.add(chapterId);
        int result = courseChapterRepository.updateIsValidatedIds(TAKE_EFFECT_CLOSE, stringSet);
        log.info("chapterId : {}, resoult : {}", chapterId, result);
    }

    /**
     * 根据科目ID查询章节信息
     * 客户端用
     * @param courseId
     * @return
     */
    @Override
    public List<CourseTreeResp> findByCourseId(String courseId){
        List<ICourseChapterDto> dtoList = courseChapterRepository.findByCourseId(courseId);
        List<CourseTreeResp> courseTreeResps = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            State state = new State();
            ICourseChapterDto courseChapterDto = dtoList.get(i);
            CourseTreeResp courseTreeResp = new CourseTreeResp();
            courseTreeResp.setId(courseChapterDto.getChapterId());
            courseTreeResp.setText(courseChapterDto.getChapterName());
            courseTreeResp.setParent(courseChapterDto.getChapterParentId());
            if (PUBLISH_YES.equals(courseChapterDto.getPublish())) {
                courseTreeResp.setIcon("fa fa-briefcase icon-state-success");
            }else if (PUBLISH_NO.equals(courseChapterDto.getPublish())){
                courseTreeResp.setIcon("fa fa-send-o icon-state-success");
            }
            if (i == 0) {
                state.setSelected(true);
            }
            courseTreeResp.setState(state);
            courseTreeResps.add(courseTreeResp);
        }
        return courseTreeResps;
    }

    /**
     * 通过父章节目录信息查询子小节信息
     * @param chapterParentId
     * @return
     */
    @Override
    public List<ICourseChapterDto> findByChapterParentId(String isValidated, String chapterParentId){
        return courseChapterRepository.findByChapterParentId(isValidated, chapterParentId);
    }
    /**
     * １. 章节ID
     * ２．是否有效　0 有效　1无效
     * 根据条件筛选查询对应的章节信息
     * @param vo
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<ICourseChapterDto> findAllCourseChapter(CourseChapterVo vo){
        return courseChapterRepository.findCourseId(vo.getIsValidated(), vo.getCourseId());
    }
}
