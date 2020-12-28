package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.OnLineDisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 10:00
 * @Version: v1.0
 * @Modified：在线网盘操作
 * @Description:
 */
public interface OnLineDiskRepository extends JpaRepository<OnLineDisk, Long> {

    @Transactional(readOnly = true)
    List<OnLineDisk> findAllByIsValidatedAndPIdAndUserId(String isValidated, long pId, String userId);


    /**
     * 删除文件列表信息
     *
     * @param ids
     */
    @Modifying(clearAutomatically = true)
    @javax.transaction.Transactional(rollbackOn = Exception.class)
    void deleteAllByIdIn(Iterable<Long> ids);
}