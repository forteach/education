package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.DatumArea;
import com.forteach.education.databank.dto.IDatumAreaCountDto;
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

    /**
     * 章节领域资料数量
     * @param courseId
     * @param datumArea
     * @param isValidated
     * @return
     */
    public int countByCourseIdAndChapterIdAndDatumAreaAndIsValidated(String courseId,String chapterId, String datumArea,String isValidated);


    /**
     * 课件列表
     * @param courseId
     * @param datumArea
     * @param isValidated
     * @return
     */
    @Query("SELECT datumType as datumType,count(datumType) as dCount from ChapteData where courseId=:courseId and chapterId=:chapterId and datumArea=:datumArea and isValidated=:isValidated group by datumType ")
    public List<IDatumAreaCountDto> countKeJian(String courseId,String chapterId, String datumArea, String isValidated);


}
