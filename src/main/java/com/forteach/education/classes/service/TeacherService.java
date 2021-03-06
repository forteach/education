package com.forteach.education.classes.service;

import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.dto.IClassesDto;
import com.forteach.education.classes.dto.TeacherCourseDto;
import com.forteach.education.classes.web.req.FindAllTeacherPage;
import com.forteach.education.web.resp.TeacherInfoResp;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 09:27
 * @Version: 1.0
 * @Description: 操作教师service层
 */
public interface TeacherService {
    /**
     * 保存教师信息
     *
     * @param teacher
     */
    Teacher save(Teacher teacher);

    /**
     * 修改教师信息
     *
     * @param teacher
     * @return
     */
    Teacher edit(Teacher teacher);

    /**
     * 根据教师 ID 删除教师信息
     *
     * @param teacherId
     * @return
     */
    void deleteById(String teacherId);

    /**
     * 删除教师信息
     *
     * @param teacher
     */
    void delete(Teacher teacher);

    /**
     * 分页查询教师信息
     *
     * @param sortVo
     * @return
     */
    Page<Teacher> findAll(FindAllTeacherPage sortVo);

    /**
     * 根据ID 查询教师信息
     *
     * @param teacherId
     * @return
     */
    Teacher getTeacherById(String teacherId);

    /**
     * 逻辑删除教师信息使其无效不显示
     *
     * @param teacherId
     */
    void deleteIsValidById(String teacherId);

    /**
     * 根据专业ID查询教师列表信息
     *
     * @param specialtyId
     * @return
     */
    List<Teacher> findTeachersBySpecialtyId(String specialtyId);

    /**
     * 查询所用有效的教师信息
     *
     * @return
     */
    List<TeacherInfoResp> findAllTeacherInfo();

    /**
     * 教师端查询我的班级信息
     *
     * @param teacherId
     * @return
     */
    List<IClassesDto> findMyTeachClassInfo(String teacherId, String courseId);

    /**
     * 查询老师课程对应所教的班级
     *
     * @param courseNumber
     * @return
     */
    List<TeacherCourseDto> findTeacherByCourseId(String courseNumber);

    Teacher findById(String teacherId);
}
