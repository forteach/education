package com.forteach.education.classes.service;

import com.forteach.education.classes.domain.Classes;
import com.forteach.education.classes.web.req.ClassesVo;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.dto.IStudentDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-11 11:06
 * @version: 1.0
 * @description:
 */
public interface ClassesService {

    /**
     * 查询所有有效的班级信息按照时间倒叙排列
     * @return　班级信息列表
     */
    public List<Classes> findAll();

    /**
     * 分页查询班级信息倒叙排列
     * @param sortVo
     * @return　分页的班级信息
     */
    public Page<Classes> pageAll(SortVo sortVo);

    /**
     * 修改班级信息
     * @param classesVo
     * @return　修改后的班级信息
     */
    public Classes editClass(ClassesVo classesVo);

    public Classes findById(String id);

    List<IStudentDto> findStudentsByClassId(String classId);

    /**
     * 逻辑删除班级信息
     * @param classId
     * @return
     */
    void removeClass(String classId);
}
