package com.forteach.education.repository;

import com.forteach.education.domain.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:41
 * @Version: 1.0
 * @Description:　科目章节
 */
public interface CourseChapterRepository extends JpaRepository<CourseChapter, String> {


    /**
     * 根据查询科目编号查询父章节编号是空的章节并按照正序排列
     * 查询条件 1、有效 2 父章节编号是空 3 按照层级正序排列
     * @param courseId　科目ID
     * @return 章节目录基本信息
     */
//    List<CourseChapterDto> findByIsValidatedEqualsAndCourseId(String courseId);
//    @Query("select course_id, chapter_name, chapterParent_id from course_chapter c where c.is_validated = '0' and  c.course_id = ?1 and c.chapter_parent_id is null ORDER BY  c.sort asc")
//    List<CourseChapterDto> findByCourseId(String courseId);

    /**
     * 根据章节ID和是否有效查询章节目录信息
     * @param isValidated　是否有效　０ 有效　１　无效
     * @param courseId　科目ID
     * @return　目录章节基本信息
     */
//    @Query(value = "select u from course_chapter c where c.is_validated = :isValidated and  c.course_id = :courseId and c.chapter_parent_id is null ORDER BY  c.sort asc")
//    List<CourseChapter> findAllCourseChapterByChapterIdAndIsValidated(@Param("isValidated") String isValidated, @Param("courseId") String courseId);
}
