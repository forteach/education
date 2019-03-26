package com.forteach.education.classes.service.imp;

import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.repository.TeacherRepository;
import com.forteach.education.classes.service.TeacherService;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.util.StringUtil;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.resp.TeacherInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_CLOSE;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 09:28
 * @Version: 1.0
 * @Description: 操作教师service层
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * 保存单个教师信息
     *
     * @param teacher
     */
    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * 编辑教师信息
     *
     * @param teacher
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Teacher edit(Teacher teacher) {
        Teacher source = teacherRepository.findById(teacher.getTeacherId()).get();
        UpdateUtil.copyNullProperties(source, teacher);
        return teacherRepository.save(teacher);
    }

    /**
     * 根据教师ID 删除教师信息
     *
     * @param teacherId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    /**
     * 删除教师对象
     *
     * @param teacher
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    /**
     * 分页查询教师信息
     *
     * @param sortVo
     * @return
     */
    @Override
    public Page<Teacher> findAll(SortVo sortVo) {
        Page<Teacher> page = teacherRepository.findByIsValidatedEqualsOrderByCreateTimeDesc(StringUtil.hasEmptyIsValidated(sortVo), PageRequest.of(sortVo.getPage(), sortVo.getSize()));
        return page;
    }

    /**
     * 根据教师ID　查询教师信息
     *
     * @param teacherId
     * @return
     */
    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId).get();
    }

    /**
     * 逻辑删除教师信息使其无效不显示
     *
     * @param teacherId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIsValidById(String teacherId) {
        teacherRepository.findById(teacherId)
                .ifPresent(t -> {
                    t.setIsValidated(TAKE_EFFECT_CLOSE);
                    teacherRepository.save(t);
                });
    }

    /**
     * 根据专业号查询教师信息列表
     *
     * @param specialtyId
     * @return
     */
    @Override
    public List<Teacher> findTeachersBySpecialtyId(String specialtyId) {
        return teacherRepository.findByIsValidatedEqualsAndSpecialtyId(TAKE_EFFECT_OPEN, specialtyId);
    }

    @Override
    public List<TeacherInfoResp> findAllTeacherInfo() {
        List<TeacherInfoResp> list = new ArrayList<>();
        teacherRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN)
                .parallelStream()
                .forEach(t -> {
                    list.add(TeacherInfoResp.builder()
                            .teacherId(t.getTeacherId())
                            .teacherCode(t.getTeacherCode())
                            .teacherName(t.getTeacherName())
                            .build());
                });
        return list;
    }


}
