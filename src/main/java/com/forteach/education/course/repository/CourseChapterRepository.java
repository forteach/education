package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseChapter;
import com.forteach.education.course.dto.ICourseChapterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:41
 * @Version: 1.0
 * @Description:　科目章节
 */
@Repository("courseChapterRepository")
public interface CourseChapterRepository extends JpaRepository<CourseChapter, String>, JpaSpecificationExecutor<CourseChapterRepository> {


    /**
     * 根据查询科目编号查询父章节编号是空的章节并按照正序排列
     * 查询条件 1、有效 2 父章节编号是空 3 按照层级正序排列
     *
     * @param courseId 　科目ID
     * @return 章节目录基本信息
     */
    @Transactional(readOnly = true)
    @Query("select chapterId as chapterId, chapterName as chapterName, chapterParentId as chapterParentId, publish as publish, sort as sort, chapterLevel as chapterLevel " +
            "from CourseChapter where  courseId = ?1 order by chapterLevel asc, sort asc")
    List<ICourseChapterDto> findByCourseId(String courseId);

    /**
     * 根据章节信息查询对应小节信息
     *
     * @param isValidated
     * @param chapterParentId
     * @return 所属的章节信息按照从顺序排列
     */
    @Transactional(readOnly = true)
    @Query("select chapterId as chapterId, chapterName as chapterName, chapterParentId as chapterParentId, publish as publish, sort as sort, chapterLevel as chapterLevel from CourseChapter" +
            " where isValidated = :isValidated and chapterParentId = :chapterParentId order by sort asc")
    List<ICourseChapterDto> findByChapterParentId(@Param("isValidated") String isValidated, @Param("chapterParentId") String chapterParentId);

    /**
     * 根据科目章节查询科目章节信息
     *
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true)
    List<CourseChapter> findByIsValidatedEqualsAndCourseIdAndChapterId(String isValidated, String courseId, String chapterId);

    /**
     * 根据章节ID和是否有效查询章节目录信息
     *
     * @param isValidated 　是否有效 0 有效 1无效
     * @param courseId    　科目ID
     * @return　目录章节基本信息
     */
    @Transactional(readOnly = true)
    @Query("select chapterId as chapterId, chapterName as chapterName, chapterParentId as chapterParentId, publish as publish, sort as sort, chapterLevel as chapterLevel " +
            "from CourseChapter where isValidated = :isValidated and  courseId = :courseId  ORDER BY  sort asc")
    List<ICourseChapterDto> findCourseId(@Param("isValidated") String isValidated, @Param("courseId") String courseId);

    /**
     * 查询有效的章节科目信息行数
     *
     * @param isValidated
     * @param courseId
     * @param chapterParentId
     * @return
     */
    @Transactional(readOnly = true)
    int countByIsValidatedEqualsAndCourseIdAndChapterParentId(String isValidated, String courseId, String chapterParentId);

    @Transactional(readOnly = true)
    List<CourseChapter> findByCourseIdAndAndChapterParentId(String courseId, String chapterParentId);

//    @Modifying
//    @Transactional
//    @Query("delete from CourseChapter c where c.chapterId in (?1)")
//    void deleteBath(List<String> ids);

    /**
     * 根据id批量删除
     */
    @Modifying
    @Transactional
    @Query(value = "delete from CourseChapter c where c.chapterId in (:ids) ")
    int deleteBathIds(@Param("ids") Set<String> ids);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = " update CourseChapter c set c.isValidated = :isValidated where c.chapterId in (:ids) ")
    int updateIsValidatedIds(@Param("isValidated") String isValidated, @Param("ids") Set<String> ids);
}
