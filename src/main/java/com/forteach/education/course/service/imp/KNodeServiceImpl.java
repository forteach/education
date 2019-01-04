package com.forteach.education.course.service.imp;

import com.forteach.education.course.domain.KNode;
import com.forteach.education.course.repository.KNodeRepository;
import com.forteach.education.course.service.KNodeService;
import com.forteach.education.course.web.req.KNodeAll;
import com.forteach.education.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.Collectors;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

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

    @Override
    public KNodeAll save(KNode kNode){

       kNodeRepository.save(kNode);

        //创建输出对象
        KNodeAll resp=new KNodeAll();
        UpdateUtil.copyNullProperties(kNode, resp);
        return resp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public KNodeAll edit(KNode kNode){
        KNode source = kNodeRepository.findById(kNode.getKNodeId()).get();
        UpdateUtil.copyNullProperties(source, kNode);
        kNodeRepository.save(kNode);

        //创建输出对象
        KNodeAll resp=new KNodeAll();
        UpdateUtil.copyNullProperties(kNode, resp);
        return resp;
    }

    /**
     * 根据章节获得知识点
     * @param chapterId
     * @return
     */
    @Override
    public List<KNodeAll> findByChapterId(String chapterId)
    {
        return kNodeRepository
                .findByChapterIdAndIsValidated(chapterId,TAKE_EFFECT_OPEN)
                .stream().map((item)->{return new KNodeAll(item.getCourseId(),item.getChapterId(),item.getKNodeId(),item.getNodeName());})
                .collect(Collectors.toList());
    }

    /**
     * 根据课程获得知识点
     * @param courseId
     * @return
     */
    @Override
    public List<KNodeAll> findByCourseId(String courseId){
        return kNodeRepository.findByCourseIdAndIsValidated(courseId,TAKE_EFFECT_OPEN)
                .stream().map((item)->{return new KNodeAll(item.getCourseId(),item.getChapterId(),item.getKNodeId(),item.getNodeName());})
                .collect(Collectors.toList());
    }


    @Override
    public KNodeAll findById(String kNodeId){
        KNode kNode= kNodeRepository.findById(kNodeId).get();

        //创建输出对象
        KNodeAll resp=new KNodeAll();
        UpdateUtil.copyNullProperties(kNode, resp);
        return resp;
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

//    /**
//     * 根据条件分页筛选查询知识点信息
//     * @param kNodeReq
//     * @return
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public Page<KNode> selectById(KNodeReq kNodeReq){
//        SortVo sortVo = kNodeReq.getSortVo();
//        return findKNodePage(kNodeReq.getSortVo().getIsValidated(),
//                kNodeReq.getCourseId(), kNodeReq.getChapterId(), kNodeReq.getDataId(), kNodeReq.getKNodeType(),
//                PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
//    }
//
//    /**
//     * 多条件分页查询知识点
//     * @param isValidated
//     * @param courseId
//     * @param chapterId
//     * @param dataId
//     * @param kNodeType
//     * @param pageable
//     * @return
//     */
//    public Page<KNode> findKNodePage(String isValidated, String courseId, String chapterId, String dataId, String kNodeType, Pageable pageable) {
//        return kNodeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicatesList = new ArrayList<Predicate>();
//            if (StrUtil.isNotBlank(isValidated)) {
//                predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), isValidated));
//            }
//            if (StrUtil.isNotBlank(courseId)) {
//                predicatesList.add(
//                        criteriaBuilder.equal(root.get("courseId"), courseId));
//            }
//            if (StrUtil.isNotBlank(chapterId)) {
//                predicatesList.add(
//                        criteriaBuilder.equal(root.get("chapterId"), chapterId));
//            }
//            if (StrUtil.isNotBlank(dataId)) {
//                predicatesList.add(
//                        criteriaBuilder.equal(root.get("dataId"), dataId));
//            }
//            if (StrUtil.isNotBlank(kNodeType)) {
//                predicatesList.add(
//                        criteriaBuilder.equal(root.get("kNodeType").as(String.class), kNodeType));
//            }
//            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
//        }, pageable);
//    }
}
