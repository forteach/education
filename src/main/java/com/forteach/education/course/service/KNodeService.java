package com.forteach.education.course.service;

import com.forteach.education.course.domain.KNode;
import com.forteach.education.web.req.KNodeReq;
import org.springframework.data.domain.Page;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:12
 * @Version: 1.0
 * @Description:
 */
public interface KNodeService {
    KNode save(KNode kNode);

    KNode edit(KNode kNode);

    Page<KNode> selectById(KNodeReq kNodeReq);

    KNode findById(String kNodeId);

    void deleteById(String kNodeId);

    void deleteIsValidById(String kNodeId);
}
