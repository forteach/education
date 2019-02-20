package com.forteach.education.course.repository.ziliao;

import com.forteach.education.course.domain.ziliao.ImportantCourseware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpCoursewareRepoitory extends JpaRepository<ImportantCourseware, String> {

    /**
     * 重要课件资料（教案、课件）图集类型除外,按章节、文件类型、课件类型查询
     *
     * @param chapterId
     * @param datumType
     * @param importantType 1、教案 2、课件
     * @param isValidated
     * @return
     */
    public List<ImportantCourseware> findByChapterIdAndDatumTypeAndImportantTypeAndIsValidated(String chapterId, String datumType, String importantType, String isValidated);


}
