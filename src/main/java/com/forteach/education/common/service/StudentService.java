package com.forteach.education.common.service;

import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.authority.web.req.FindAllPageStudentReq;
import com.forteach.education.common.web.vo.StudentInfoVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-14 14:41
 * @version: 1.0
 * @description:
 */
public interface StudentService {

    /**
     * 学生id获取学生信息
     *
     * @param studentId
     * @return
     */
    public StudentEntitys getStudentEntitysById(String studentId);

    /**
     * 学生信息students 集合list转换为学生信息
     *
     * @param stringList
     * @return
     */
    public List<StudentEntitys> getStudentEntitysList(List<String> stringList);

    /**
     * 逗号分割的学生id字符串数组转换学生信息
     *
     * @param studentIds
     * @return
     */
    public List<StudentEntitys> getStudentListByStr(String studentIds);

    Page<StudentEntitys> findAllPage(FindAllPageStudentReq req);

    /**
     * 微信学生端查询我的信息
     * @param studentId
     * @return
     */
    public StudentInfoVo studentInfoByStudentId(String studentId);
}
