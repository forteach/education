package com.forteach.education.databank.repository;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.databank.domain.ChapteData;
import com.forteach.education.databank.domain.FileDatum;
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
 * @Date: 2018/12/18 22:20
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class DatabankRepositoryIml {
    @Resource
    private ChapteDataRepository chapteDataRepository;

    @Resource
    private FileDatumRepository fileDatumRepository;
    /**
     * 动态查询知识挂载
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param mount
     * @param pageable
     * @return
     */
    public Page<ChapteData> findChapteData(String isValidated, String courseId, String chapterId, String mount, Pageable pageable) {
        return chapteDataRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (StrUtil.isNotBlank(isValidated)){
                predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), isValidated));
            }
            if (StrUtil.isNotBlank(courseId)){
                predicatesList.add(criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (StrUtil.isNotBlank(chapterId)){
                predicatesList.add(criteriaBuilder.equal(root.get("chapterId"), chapterId));
            }
            if (StrUtil.isNotBlank(mount)) {
                predicatesList.add(criteriaBuilder.equal(root.get("mount"), mount));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);
    }
    /**
     * 分页动态查询有无效文件信息
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param mount
     * @param pageable
     * @return
     */
    public Page<FileDatum> findChapteFiles(String isValidated, String courseId, String chapterId, String mount, Pageable pageable) {
        return fileDatumRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (StrUtil.isNotBlank(isValidated)){
                predicatesList.add(criteriaBuilder.equal(root.get("isValidated"), isValidated));
            }
            if (StrUtil.isNotBlank(courseId)){
                predicatesList.add(criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (StrUtil.isNotBlank(chapterId)){
                predicatesList.add(criteriaBuilder.equal(root.get("chapterId"), chapterId));
            }
            if (StrUtil.isNotBlank(mount)) {
                predicatesList.add(criteriaBuilder.equal(root.get("mount"), mount));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, pageable);
    }
}
