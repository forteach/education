package com.forteach.education.course.repository.ziliao;

import com.forteach.education.course.domain.ziliao.ImportantCourseware;
import com.forteach.education.databank.dto.IDatumAreaCountDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImpCoursewareRepoitory extends IFileRepoitory<ImportantCourseware, String> {

    /**
     *
     * @param chapterId
     * @param datumType
     * @param importantType 1、教案 2、课件
     * @param isValidated
     * @return
     */
    public List<ImportantCourseware> findByChapterIdAndDatumTypeAndImportantTypeAndIsValidated( String chapterId, String datumType,String importantType, String isValidated);

    /**
     *重要课件数量
     * @param isValidated
     * @return
     */
    @Query("SELECT datumType as datumType,count(datumType) as dCount from ChapteData where   chapterId=:chapterId and importantType=:importantType and isValidated=:isValidated group by datumType ")
    public List<IDatumAreaCountDto> countKeJian( String chapterId, String importantType, String isValidated);

}
