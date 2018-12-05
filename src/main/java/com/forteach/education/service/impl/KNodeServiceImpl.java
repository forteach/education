package com.forteach.education.service.impl;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.KNode;
import com.forteach.education.repository.KNodeRepository;
import com.forteach.education.service.KNodeService;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.KNodeReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:12
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class KNodeServiceImpl implements KNodeService {
    @Resource
    private KNodeRepository kNodeRepository;

    @Override
    public KNode save(KNode kNode){
        return kNodeRepository.save(kNode);
    }

    @Override
    public KNode edit(KNode kNode){
        KNode source = kNodeRepository.findById(kNode.getKNodeId()).get();
        UpdateUtil.copyNullProperties(source, kNode);
        return kNodeRepository.save(kNode);
    }

    @Override
    public WebResult selectById(KNodeReq kNodeReq){
        int type = kNodeReq.getSelectType();
        List<KNode> list = null;
        if (1 == type){
            list = kNodeRepository.findByIsValidatedEqualsAndCourseId(TAKE_EFFECT_OPEN, kNodeReq.getSelectId());
        }else if (2 == type){
            list = kNodeRepository.findByIsValidatedEqualsAndCourseId(TAKE_EFFECT_OPEN, kNodeReq.getSelectId());
        }else if (3 == type){
            list = kNodeRepository.findByIsValidatedEqualsAndDataId(TAKE_EFFECT_OPEN, kNodeReq.getSelectId());
        }else {
            return WebResult.failException("查询参数错误");
        }
        return WebResult.okResult(list);
    }
}
