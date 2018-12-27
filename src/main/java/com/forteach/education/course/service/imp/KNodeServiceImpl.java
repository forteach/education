package com.forteach.education.course.service.imp;

import com.forteach.education.course.domain.KNode;
import com.forteach.education.course.repository.KNodeRepository;
import com.forteach.education.course.service.KNodeService;
import com.forteach.education.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;

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

    /**
     * 根据章节获得知识点
     * @param chapterId
     * @return
     */
    @Override
    public List<KNode> findByChapterId(String chapterId){
        return kNodeRepository.findByChapterId(chapterId);
    }

    /**
     * 根据课程获得知识点
     * @param courseId
     * @return
     */
    @Override
    public List<KNode> findByCourseId(String courseId){
        return kNodeRepository.findByCourseId(courseId);
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
