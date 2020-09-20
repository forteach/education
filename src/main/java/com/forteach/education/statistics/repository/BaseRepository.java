package com.forteach.education.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/20 17:18
 * @Version: v1.0
 * @Modified：]
 * @Description:
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    /**
     * 查询全部有效信息
     * @param isValidated
     * @return
     */
    @Transactional(readOnly = true)
    List<T> findAllByIsValidatedEquals(String isValidated);
}
