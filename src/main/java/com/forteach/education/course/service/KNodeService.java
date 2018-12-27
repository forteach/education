package com.forteach.education.course.service;

import com.forteach.education.course.domain.KNode;
import com.forteach.education.web.req.KNodeReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:12
 * @Version: 1.0
 * @Description:
 */
public interface KNodeService {
    public KNode save(KNode kNode);

    public  KNode edit(KNode kNode);

    public List<KNode> findByChapterId(String chapterId);

    public List<KNode> findByCourseId(String courseId);

   // public Page<KNode> selectById(KNodeReq kNodeReq);

    public KNode findById(String kNodeId);

    public void deleteById(String kNodeId);

    public void deleteIsValidById(String kNodeId);

    //public Page<KNode> findKNodePage(String isValidated, String courseId, String chapterId, String dataId, String kNodeType, Pageable pageable);
}
