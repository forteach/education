package com.forteach.education.course.service;

import com.forteach.education.course.domain.KNode;
import com.forteach.education.course.web.req.KNodeAll;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:12
 * @Version: 1.0
 * @Description:
 */
public interface KNodeService {
    public KNodeAll save(KNode kNode);

    public  KNodeAll edit(KNode kNode);

    public List<KNodeAll> findByChapterId(String chapterId);

    public List<KNodeAll> findByCourseId(String courseId);

   // public Page<KNode> selectById(KNodeReq kNodeReq);

    public KNodeAll findById(String kNodeId);

    public void deleteById(String kNodeId);

    public void deleteIsValidById(String kNodeId);

    //public Page<KNode> findKNodePage(String isValidated, String courseId, String chapterId, String dataId, String kNodeType, Pageable pageable);
}
