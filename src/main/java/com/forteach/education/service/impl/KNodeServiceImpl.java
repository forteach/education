package com.forteach.education.service.impl;

import com.forteach.education.domain.KNode;
import com.forteach.education.repository.KNodeRepository;
import com.forteach.education.repository.RepositoryImpl;
import com.forteach.education.service.KNodeService;
import com.forteach.education.util.SortUtil;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.KNodeReq;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.forteach.education.common.Dic.TAKE_EFFECT_CLOSE;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:12
 * @Version: 1.0
 * @Description: 操作知识点　Service
 */
@Slf4j
@Service
public class KNodeServiceImpl implements KNodeService {
    @Resource
    private KNodeRepository kNodeRepository;
    @Resource
    private RepositoryImpl repository;

    @Override
    public KNode save(KNode kNode){
        return kNodeRepository.save(kNode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public KNode edit(KNode kNode){
        KNode source = kNodeRepository.findById(kNode.getKNodeId()).get();
        UpdateUtil.copyNullProperties(source, kNode);
        return kNodeRepository.save(kNode);
    }

    @Override
    public KNode findById(String kNodeId){
        return kNodeRepository.findById(kNodeId).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void deleteById(String kNodeId) {
        kNodeRepository.deleteById(kNodeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void deleteIsValidById(String kNodeId) {
        KNode kNode = kNodeRepository.findById(kNodeId).get();
        kNode.setIsValidated(TAKE_EFFECT_CLOSE);
        kNodeRepository.save(kNode);
    }

    /**
     * 根据条件分页筛选查询知识点信息
     * @param kNodeReq
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KNode> selectById(KNodeReq kNodeReq){
        SortVo sortVo = kNodeReq.getSortVo();
        return repository.findKNodePage(kNodeReq.getSortVo().getIsValidated(),
                kNodeReq.getCourseId(), kNodeReq.getChapterId(), kNodeReq.getDataId(), kNodeReq.getKNodeType(),
                PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
    }
}
