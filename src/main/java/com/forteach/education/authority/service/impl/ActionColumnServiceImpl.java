package com.forteach.education.authority.service.impl;

import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.authority.repository.ActionColumnRepository;
import com.forteach.education.authority.service.ActionColumnService;
import com.forteach.education.authority.web.req.ActionColumnReq;
import com.forteach.education.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 09:43
 * @Version: 1.0
 * @Description: 系统栏目
 */
@Slf4j
@Service
public class ActionColumnServiceImpl implements ActionColumnService {

    private final ActionColumnRepository actionColumnRepository;

    @Autowired
    public ActionColumnServiceImpl(ActionColumnRepository actionColumnRepository) {
        this.actionColumnRepository = actionColumnRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionColumn editSaveActionColumn(ActionColumnReq actionColumnReq) {
        Optional<ActionColumn> actionColumnOptional = actionColumnRepository.findById(actionColumnReq.getColId() != null ? actionColumnReq.getColId() : "");
        ActionColumn actionColumn;
        if (actionColumnOptional.isPresent()) {
            actionColumn = actionColumnOptional.get();
        } else {
            ActionColumn andColName = actionColumnRepository.findByIsValidatedEqualsAndColName(TAKE_EFFECT_OPEN, actionColumnReq.getColName());
            if (andColName != null) {
                throw new RuntimeException("存在相同栏目名");
            }
            actionColumn = new ActionColumn();
        }
        UpdateUtil.copyNullProperties(actionColumnReq, actionColumn);
        return actionColumnRepository.save(actionColumn);
    }
}
