package com.forteach.education.service.impl;

import com.forteach.education.domain.Teacher;
import com.forteach.education.repository.TeacherRepository;
import com.forteach.education.service.TeacherService;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 09:28
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * 保存单个教师信息
     * @param teacher
     */
    @Override
    public Teacher save(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    /**
     * 编辑教师信息
     * @param teacher
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Teacher edit(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    /**
     * 根据教师ID 删除教师信息
     * @param teacherId
     */
    @Override
    public void deleteById(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    /**
     * 删除教师对象
     * @param teacher
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Teacher teacher){
        teacherRepository.delete(teacher);
    }

    /**
     * 分页查询教师信息
     * @param sortVo
     * @return
     */
    @Override
    public Page<Teacher> pageTeachers(SortVo sortVo){
        Sort sort = new Sort(Sort.Direction.DESC, sortVo.getSorting());
        return teacherRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN, PageRequest.of(sortVo.getPage(), sortVo.getSize(), sort));
    }

    /**
     * 根据教师ID　查询教师信息
     * @param teacherId
     * @return
     */
    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId).get();
    }


}
