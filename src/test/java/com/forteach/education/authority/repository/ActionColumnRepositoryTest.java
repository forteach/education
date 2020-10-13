package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.ActionColumn;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-7 11:22
 * @version: 1.0
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionColumnRepositoryTest {
    @Resource
    private ActionColumnRepository actionColumnRepository;

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void save() {
        List<ActionColumn> list = new ArrayList<>();
        ActionColumn actionColumn1 = new ActionColumn();
        actionColumn1.setColName("课程管理");
        actionColumn1.setColImgUrl("icon-basket");
        actionColumn1.setColUrl("javascript:;");
        actionColumn1.setIsOrder(1);
        ActionColumn actionColumnSave1 = actionColumnRepository.save(actionColumn1);

        ActionColumn actionColumn11 = new ActionColumn();
        actionColumn11.setColName("课程添加");
        actionColumn11.setColImgUrl("icon-puzzle");
        actionColumn11.setColUrl("#/courseAdd.html");
        actionColumn11.setIsOrder(1);
        list.add(actionColumn11);
//            list.add(actionColumn1);
        ActionColumn actionColumn12 = new ActionColumn();
        actionColumn12.setColName("课程添加");
        actionColumn12.setColImgUrl("icon-puzzle");
        actionColumn12.setColUrl("#/courseAdd.html");
        actionColumn12.setIsOrder(2);
        actionColumn12.setColParentId(actionColumnSave1.getColId());
        list.add(actionColumn12);

        ActionColumn actionColumn13 = new ActionColumn();
        actionColumn13.setColName("我的课程");
        actionColumn13.setColImgUrl("icon-paper-clip");
        actionColumn13.setColUrl("#/myCourses.html");
        actionColumn13.setIsOrder(3);
        actionColumn13.setColParentId(actionColumnSave1.getColId());
        list.add(actionColumn13);


        ActionColumn actionColumn2 = new ActionColumn();
        actionColumn2.setColName("资料库管理");
        actionColumn2.setColImgUrl("icon-rocket");
        actionColumn2.setColUrl("javascript:;");
        actionColumn2.setIsOrder(2);
        ActionColumn actionColumnSave2 = actionColumnRepository.save(actionColumn2);
        ActionColumn actionColumn21 = new ActionColumn();
        actionColumn21.setColName("备课参考");
        actionColumn21.setColImgUrl("icon-paper-clip");
        actionColumn21.setColUrl("#/assistant.html");
        actionColumn21.setIsOrder(1);
        actionColumn21.setColParentId(actionColumnSave2.getColId());
        list.add(actionColumn21);

        ActionColumn actionColumn22 = new ActionColumn();
        actionColumn22.setColName("知识点题库");
        actionColumn22.setColImgUrl("icon-puzzle");
        actionColumn22.setColUrl("#/knowledgePoint.html");
        actionColumn22.setIsOrder(2);
        actionColumn22.setColParentId(actionColumnSave2.getColId());
        list.add(actionColumn22);

        ActionColumn actionColumn23 = new ActionColumn();
        actionColumn23.setColName("教辅题册");
        actionColumn23.setColImgUrl("icon-paper-clip");
        actionColumn23.setColUrl("#/xitice.html");
        actionColumn23.setIsOrder(3);
        actionColumn23.setColParentId(actionColumnSave2.getColId());
        list.add(actionColumn23);

        ActionColumn actionColumn24 = new ActionColumn();
        actionColumn24.setColName("任务和风暴");
        actionColumn24.setColImgUrl("icon-paper-clip");
        actionColumn24.setColUrl("#/task.html");
        actionColumn24.setIsOrder(4);
        actionColumn24.setColParentId(actionColumnSave2.getColId());
        list.add(actionColumn24);

        ActionColumn actionColumn25 = new ActionColumn();
        actionColumn25.setColName("问卷");
        actionColumn25.setColImgUrl("icon-paper-clip");
        actionColumn25.setColUrl("#/survey.html");
        actionColumn25.setIsOrder(5);
        actionColumn25.setColParentId(actionColumnSave2.getColId());
        list.add(actionColumn25);
//            list.add(actionColumn2);

        ActionColumn actionColumn3 = new ActionColumn();
        actionColumn3.setColName("教学互动");
        actionColumn3.setColImgUrl("icon-diamond");
        actionColumn3.setColUrl("javascript:;");
        actionColumn3.setIsOrder(3);
        ActionColumn actionColumnSave3 = actionColumnRepository.save(actionColumn3);

        ActionColumn actionColumn31 = new ActionColumn();
        actionColumn31.setColName("课程总览");
        actionColumn31.setColImgUrl("icon-puzzle");
        actionColumn31.setColUrl("#/cl6.html");
        actionColumn31.setIsOrder(1);
        actionColumn31.setColParentId(actionColumnSave3.getColId());
        list.add(actionColumn31);

        ActionColumn actionColumn32 = new ActionColumn();
        actionColumn32.setColName("上课");
        actionColumn32.setColImgUrl("icon-puzzle");
        actionColumn32.setColUrl("#/cl4.html");
        actionColumn32.setIsOrder(2);
        actionColumn32.setColParentId(actionColumnSave3.getColId());
        list.add(actionColumn32);
//        list.add(actionColumn3);


        ActionColumn actionColumn4 = new ActionColumn();
        actionColumn4.setColName("教学统计");
        actionColumn4.setColImgUrl("icon-settings");
        actionColumn4.setColUrl("#/statistics.html");
        actionColumn4.setIsOrder(4);
//        ActionColumn actionColumnSave4 = actionColumnRepository.save(actionColumn4);
        list.add(actionColumn4);

        ActionColumn actionColumn5 = new ActionColumn();
        actionColumn5.setColName("教师管理");
        actionColumn5.setColImgUrl("icon-settings");
        actionColumn5.setColUrl("#/teacher.html");
        actionColumn5.setIsOrder(5);
//        ActionColumn actionColumnSave5 = actionColumnRepository.save(actionColumn5);
        list.add(actionColumn5);


        actionColumnRepository.saveAll(list);
    }

}