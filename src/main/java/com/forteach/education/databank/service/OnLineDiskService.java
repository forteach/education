package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.OnLineDisk;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 10:08
 * @Version: v1.0
 * @Modified：在线网盘
 * @Description:
 */
public interface OnLineDiskService {

    /**
     * 查询文件列表
     *
     * @param pId
     * @param userId
     * @return
     */
    List<OnLineDisk> list(long pId, String userId);

    /**
     * 删除文件列表及其子项
     *
     * @param list
     */
    void delete(Set<Long> list);

    /**
     * 保存文件到网盘列表信息
     *
     * @param onLineDisk
     */
    void save(OnLineDisk onLineDisk);

    /**
     * 移动文件或者文件夹
     *
     * @param id
     * @param pId
     */
    void move(Long id, Long pId);
}
