package com.forteach.education.authority.service;

import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.authority.web.req.ActionColumnReq;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 09:43
 * @Version: 1.0
 * @Description: 系统栏目
 */
public interface ActionColumnService {
    /**
     * 　编辑或保存菜单栏目
     * @param actionColumnReq
     * @return
     */
    ActionColumn editSaveActionColumn(ActionColumnReq actionColumnReq);
}
