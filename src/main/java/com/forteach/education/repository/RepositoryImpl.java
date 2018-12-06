package com.forteach.education.repository;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.domain.KNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-6 10:52
 * @Version: 1.0
 * @Description:
 */
@Service
public class RepositoryImpl {

    @Resource
    private KNodeRepository kNodeRepository;

    /**
     * 多条件分页查询知识点
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param dataId
     * @param kNodeType
     * @param pageable
     * @return
     */
    public Page<KNode> findKNodePage(String isValidated, String courseId, String chapterId, String dataId, String kNodeType, Pageable pageable){
        return kNodeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<Predicate>();
            if (StrUtil.isNotBlank(isValidated)) {
                predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), isValidated));
            }
            if (StrUtil.isNotBlank(courseId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (StrUtil.isNotBlank(chapterId)){
                predicatesList.add(
                        criteriaBuilder.equal(root.get("chapterId"), chapterId));
            }
            if (StrUtil.isNotBlank(dataId)){
                predicatesList.add(
                        criteriaBuilder.equal(root.get("dataId"), dataId));
            }
            if (StrUtil.isNotBlank(kNodeType)){
                predicatesList.add(
                        criteriaBuilder.equal(root.get("kNodeType").as(String.class), kNodeType));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);
    }
}
