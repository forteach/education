package com.forteach.education.service;

import com.forteach.education.domain.Teacher;
import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Page;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 09:27
 * @Version: 1.0
 * @Description:
 */
public interface TeacherService {
    /**
     * 保存教师信息
     * @param teacher
     */
    Teacher save(Teacher teacher);

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    Teacher edit(Teacher teacher);

    /**
     * 根据教师 ID 删除教师信息
     * @param teacherId
     * @return
     */
    void deleteById(String teacherId);

    /**
     * 删除教师信息
     * @param teacher
     */
    void delete(Teacher teacher);

    /**
     * 分页查询教师信息
     * @param sortVo
     * @return
     */
    Page<Teacher> pageTeachers(SortVo sortVo);

    /**
     * 根据ID 查询教师信息
     * @param teacherId
     * @return
     */
    Teacher getTeacherById(String teacherId);
}