package com.forteach.education.course.repository;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.course.domain.KNode;
import lombok.extern.slf4j.Slf4j;
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
 * @Date: 2018/12/18 22:25
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class CourseRepositoryImpl {
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
    public Page<KNode> findKNodePage(String isValidated, String courseId, String chapterId, String dataId, String kNodeType, Pageable pageable) {
        return kNodeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<Predicate>();
            if (StrUtil.isNotBlank(isValidated)) {
                predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), isValidated));
            }
            if (StrUtil.isNotBlank(courseId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (StrUtil.isNotBlank(chapterId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("chapterId"), chapterId));
            }
            if (StrUtil.isNotBlank(dataId)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("dataId"), dataId));
            }
            if (StrUtil.isNotBlank(kNodeType)) {
                predicatesList.add(
                        criteriaBuilder.equal(root.get("kNodeType").as(String.class), kNodeType));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);
    }
}
