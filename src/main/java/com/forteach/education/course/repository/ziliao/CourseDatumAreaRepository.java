package com.forteach.education.course.repository.ziliao;

import com.forteach.education.course.domain.ziliao.CourseDatumArea;
import com.forteach.education.databank.domain.ziliao.DatumArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:44
 * @Version: 1.0
 * @Description: 章节资料
 */
public interface CourseDatumAreaRepository extends JpaRepository<CourseDatumArea, String> {

    //章节，资料领域
    public Page<CourseDatumArea> findByChapterIdAndDatumAreaIn(String chapterId, List<String> datumAreas, Pageable pageable);

    //章节、知识点、资料领域
    public Page<CourseDatumArea> findByChapterIdAndKNodeIdAndDatumAreaIn(String chapterId, String kNodeId, List<String> datumAreas, Pageable pageable);

}
