package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.SysAction;
import com.forteach.education.common.keyword.Dic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-8 16:48
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysActionRepositoryTest {
    @Resource
    private SysActionRepository sysActionRepository;
    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void save(){
        List<SysAction> list = new ArrayList<>();
        SysAction sysActionAdd = new SysAction();
        sysActionAdd.setSysActName("添加");
        sysActionAdd.setIsValidated(Dic.TAKE_EFFECT_OPEN);
        list.add(sysActionAdd);

        SysAction sysActionEdit = new SysAction();
        sysActionEdit.setSysActName("修改");
        sysActionEdit.setIsValidated(Dic.TAKE_EFFECT_OPEN);
        list.add(sysActionEdit);

        SysAction sysActionSelect = new SysAction();
        sysActionSelect.setSysActName("查看");
        sysActionSelect.setIsValidated(Dic.TAKE_EFFECT_OPEN);
        list.add(sysActionSelect);

        SysAction sysActionDelete = new SysAction();
        sysActionDelete.setSysActName("物理删除");
        sysActionDelete.setIsValidated(Dic.TAKE_EFFECT_OPEN);
        list.add(sysActionDelete);

        SysAction sysActionRemove = new SysAction();
        sysActionRemove.setSysActName("逻辑删除");
        sysActionRemove.setIsValidated(Dic.TAKE_EFFECT_OPEN);
        list.add(sysActionRemove);
        sysActionRepository.saveAll(list);
    }

}