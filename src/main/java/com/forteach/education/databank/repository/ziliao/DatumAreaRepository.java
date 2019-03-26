package com.forteach.education.databank.repository.ziliao;

import com.forteach.education.databank.domain.ziliao.DatumArea;
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
 * @Description: 章节资料
 */
public interface DatumAreaRepository extends JpaRepository<DatumArea, String> {

    /**
     * 文件编号、单个资料领域
     * @param fileId
     * @param datumArea
     * @return
     */
    @Transactional(readOnly = true)
    public DatumArea findByFileIdAndDatumArea(String fileId, String datumArea);

    /**
     * 文件编号、单个资料领域
     * @param fileId
     * @return
     */
    @Transactional(readOnly = true)
    public List<DatumArea> findByFileId(String fileId);

    /**
     * 章节，资料领域范围
     * @param chapterId
     * @param datumAreas
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<DatumArea> findByChapterIdAndDatumAreaIn(String chapterId, List<String> datumAreas, Pageable pageable);

    /**
     * 章节、知识点、资料领域
     * @param chapterId
     * @param kNodeId
     * @param datumAreas
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<DatumArea> findByChapterIdAndKNodeIdAndDatumAreaIn(String chapterId, String kNodeId, List<String> datumAreas, Pageable pageable);

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
