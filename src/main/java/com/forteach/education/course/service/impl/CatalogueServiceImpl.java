package com.forteach.education.course.service.impl;

import com.forteach.education.course.domain.Catalogue;
import com.forteach.education.course.dto.ICourseChapterDto;
import com.forteach.education.course.repository.ziliao.CatalogueRepository;
import com.forteach.education.course.service.CatalogueService;
import com.forteach.education.course.web.req.CourseChapterEditReq;
import com.forteach.education.course.web.res.CourseChapterSaveResp;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.resp.CourseTreeResp;
import com.forteach.education.web.resp.State;
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
public class CatalogueServiceImpl implements CatalogueService {


    @Resource
    private CatalogueRepository catalogueRepository;

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public CourseChapterSaveResp save(Catalogue courseChapter) {
        //1、判断是顶层章节，设置目录层级为1
        if (COURSE_CHAPTER_CHAPTER_PARENT_ID.equals(courseChapter.getChapterParentId())) {
            courseChapter.setChapterLevel("1");

        } else {
            //获得当前层级+1
            catalogueRepository.findById(courseChapter.getChapterParentId())
                    .ifPresent(c -> {
                        courseChapter.setChapterLevel(String.valueOf(Integer.parseInt(1 + c.getChapterLevel())));
                    });
        }
        //2、查询当前科目章节有多少条数据
        int count = catalogueRepository.countByIsValidatedEqualsAndCourseIdAndChapterParentId(TAKE_EFFECT_OPEN, courseChapter.getCourseId(), courseChapter.getChapterParentId());

        //3、设置当前章节下的最大序号
        courseChapter.setSort(String.valueOf(count + 1));
        catalogueRepository.save(courseChapter);

        //4、创建输出对象
        CourseChapterSaveResp resp = new CourseChapterSaveResp();
        UpdateUtil.copyNullProperties(courseChapter, resp);
        return resp;
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public CourseChapterSaveResp edit(CourseChapterEditReq courseChapterEditReq) {
        //1、获得当前数据库对象
        CourseChapterSaveResp resp = new CourseChapterSaveResp();
        catalogueRepository.findById(courseChapterEditReq.getChapterId())
                .ifPresent(source -> {
                    Catalogue courseChapter = Catalogue.builder().build();
                    BeanUtils.copyProperties(courseChapterEditReq, courseChapter);
                    UpdateUtil.copyNullProperties(source, courseChapter);
                    //2、设置创建时间
                    courseChapter.setCreateTime(source.getCreateTime());
                    catalogueRepository.save(courseChapter);

                    //3、创建输出对象
                    UpdateUtil.copyNullProperties(courseChapter, resp);
                });
        return resp;
    }

    @Override
    public CourseChapterSaveResp getCourseChapterById(String chapterId) {
        CourseChapterSaveResp resp = new CourseChapterSaveResp();
        catalogueRepository.findById(chapterId)
                .ifPresent(courseChapter -> {
                    //创建输出对象
                    UpdateUtil.copyNullProperties(courseChapter, resp);
                });
        return resp;
    }


    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void delete(Catalogue courseChapter) {
        catalogueRepository.delete(courseChapter);
    }

    /**
     * 删除树状结构科目课程章节及子章节
     *
     * @param chapterId
     */
    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void deleteById(String chapterId) {
        Catalogue courseChapter = catalogueRepository.findById(chapterId).get();
        Set<String> stringSet = findLists(courseChapter.getCourseId(), chapterId);
        stringSet.add(chapterId);
        int result = catalogueRepository.deleteBathIds(stringSet);
        log.info("chapterId : {}, deleteBath : {}", result);
    }

    /**
     * 根据父节点查询子章节的ID信息
     *
     * @param courseId
     * @param chapterParentId
     * @return
     */
    private Set<String> findLists(String courseId, String chapterParentId) {
        List<Catalogue> lists = catalogueRepository.findByCourseIdAndAndChapterParentId(courseId, chapterParentId);
        Set<String> stringSet = lists.stream().filter(courseChapter -> !COURSE_CHAPTER_CHAPTER_PARENT_ID.equals(courseChapter.getChapterParentId()))
                .map(Catalogue::getChapterId)
                .collect(Collectors.toSet());
        stringSet.parallelStream().map(s -> {
            //查询对应的目录集合
            return findLists(s, courseId);
        });
        return stringSet;
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public void deleteIsValidById(String chapterId) {
        Catalogue courseChapter = catalogueRepository.findById(chapterId).get();
        Set<String> stringSet = findLists(courseChapter.getCourseId(), chapterId);
        stringSet.add(chapterId);
        int result = catalogueRepository.updateIsValidatedIds(TAKE_EFFECT_CLOSE, stringSet);
        log.info("chapterId : {}, resoult : {}", chapterId, result);
    }

    /**
     * 根据科目ID查询章节信息
     * 客户端用
     *
     * @param courseId
     * @return
     */
    @Override
    public List<CourseTreeResp> findByCourseId(String courseId) {
        List<ICourseChapterDto> dtoList = catalogueRepository.findByCourseId(courseId);
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
            } else if (PUBLISH_NO.equals(courseChapterDto.getPublish())) {
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

}
