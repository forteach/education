package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.DatumArea;
import com.forteach.education.databank.dto.IDatumAreaCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:44
 * @Version: 1.0
 * @Description: 章节资料
 */
public interface DatumAreaRepository extends JpaRepository<DatumArea, String> {

    //章节，资料领域
    public Page<DatumArea> findByChapterIdAndDatumAreaIn(String chapterId, List<String> datumAreas,Pageable pageable);

    //章节、知识点、资料领域
    public Page<DatumArea> findByChapterIdAndKNodeIdAndDatumAreaIn(String chapterId,String kNodeId,List<String> datumAreas,Pageable pageable);

    /**
     * 章节领域资料数量
     * @param courseId
     * @param datumArea
     * @param isValidated
     * @return
     */
    public int countByCourseIdAndChapterIdAndDatumAreaAndIsValidated(String courseId,String chapterId, String datumArea,String isValidated);





}
