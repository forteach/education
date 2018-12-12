package com.forteach.education.repository;

import com.forteach.education.domain.CourseChapter;
import com.forteach.education.dto.CourseChapterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:41
 * @Version: 1.0
 * @Description:　科目章节
 */
@Repository
public interface CourseChapterRepository extends JpaRepository<CourseChapter, String>{


    /**
     * 根据查询科目编号查询父章节编号是空的章节并按照正序排列
     * 查询条件 1、有效 2 父章节编号是空 3 按照层级正序排列
     * @param courseId　科目ID
     * @return 章节目录基本信息
     */
    @Query("select new com.forteach.education.dto.CourseChapterDto(chapterId, chapterName, chapterParentId, publish, sort) " +
            "from CourseChapter where isValidated = '0' and courseId = ?1 order by sort asc")
    List<CourseChapterDto> findByCourseId(String courseId);

    /**
     * 根据章节信息查询对应小节信息
     * @param courseId
     * @param chapterParentId
     * @return 所属的章节信息按照从顺序排列
     */
    @Query("select new com.forteach.education.dto.CourseChapterDto(chapterId, chapterName, chapterParentId, publish, sort) from CourseChapter" +
            " where isValidated = '0' and courseId = :courseId and chapterParentId = :chapterParentId order by sort asc")
    List<CourseChapterDto> findByChapterParentId(@Param("courseId") String courseId, @Param("chapterParentId") String chapterParentId);

    /**
     * 根据章节ID和是否有效查询章节目录信息
     * @param isValidated　是否有效 0 有效 1无效
     * @param courseId　科目ID
     * @return　目录章节基本信息
     */
    @Query("select new com.forteach.education.domain.CourseChapter(courseId, chapterName, chapterParentId, sort, publish)" +
            "from CourseChapter where isValidated = :isValidated and  courseId = :courseId and chapterParentId is null ORDER BY  sort asc")
    List<CourseChapter> findAllCourseChapterByChapterIdAndIsValidated(@Param("isValidated") String isValidated, @Param("courseId") String courseId);
}
