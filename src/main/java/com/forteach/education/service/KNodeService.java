package com.forteach.education.service;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.KNode;
import com.forteach.education.web.req.KNodeReq;

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

    WebResult selectById(KNodeReq kNodeReq);
}
