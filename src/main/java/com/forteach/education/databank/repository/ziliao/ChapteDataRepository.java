package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.ChapteData;
import com.forteach.education.databank.dto.IChapterDataListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:44
 * @Version: 1.0
 * @Description: 章节资料
 */
public interface ChapteDataRepository extends JpaRepository<ChapteData, String> {
    /**
     * 查询科目有效资料信息
     * @param isValidated
     * @param courseId
     * @return
     */
   public  List<IChapterDataListDto> findByCourseIdAndIsValidated( String courseId,String isValidated);
    /**
     * 根据有效状态可科目ID查询章节资料信息
     * @param isValidated
     * @param chapterId
     * @return
     */
   public  List<IChapterDataListDto> findByChapterIdAndIsValidated(String chapterId,String isValidated);

    /**
     * 根据知识点获得资料列表
     * @param kNodeId
     * @param isValidated
     * @return
     */
   public List<IChapterDataListDto> findByKNodeIdAndIsValidated(String kNodeId,String isValidated);

    /**
     * 多条件查询课程科目文件挂载
     * @param specification
     * @param pageable
     * @return
     */
   public Page<ChapteData> findAll(Specification<ChapteData> specification, Pageable pageable);

   public List<IChapterDataListDto> findByCourseIdAndChapterIdAndDatumAreaAndIsValidated(String courseId,String chapterId, String datumArea,String isValidated);


}
