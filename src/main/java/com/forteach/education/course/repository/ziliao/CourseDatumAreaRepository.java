package com.forteach.education.course.repository.ziliao;

import com.forteach.education.course.domain.ziliao.CourseDatumArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:44
 * @Version: 1.0
 * @Description: 章节资料所属的领域（预习、备课。。。。）
 */
public interface CourseDatumAreaRepository extends JpaRepository<CourseDatumArea, String> {

    //文件编号、单个资料领域
    @Transactional(readOnly = true)
    public CourseDatumArea findByFileIdAndDatumArea(String fileId, String datumArea);

    //文件编号、单个资料领域
    @Transactional(readOnly = true)
    public List<CourseDatumArea> findByFileId(String fileId);


    //章节，资料领域
    @Transactional(readOnly = true)
    public Page<CourseDatumArea> findByChapterIdAndDatumAreaIn(String chapterId, List<String> datumAreas, Pageable pageable);

    //章节、知识点、资料领域
    @Transactional(readOnly = true)
    public Page<CourseDatumArea> findByChapterIdAndKNodeIdAndDatumAreaIn(String chapterId, String kNodeId, List<String> datumAreas, Pageable pageable);

    @Transactional(rollbackFor = Exception.class)
    public int deleteByChapterId(String chapterId);

    /**
     * 根据文件编号和资料领域删除信息
     * @param fileId
     * @param datumArea
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    public int deleteByFileIdAndDatumArea(String fileId, String datumArea);

}
