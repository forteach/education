package com.forteach.education.course.service;

import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.domain.Specialty;
import org.springframework.data.domain.Page;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 14:54
 * @Version: 1.0
 * @Description:
 */
public interface SpecialtyService {

    Specialty save(String specialtyName);

    Specialty edit(Specialty specialty);

    Specialty getSpecialtyById(String specialtyId);

    /**
     * 物理删除专业信息
     *
     * @param specialty
     */
    void delete(Specialty specialty);

    /**
     * 物理删除专业信息
     *
     * @param specialtyId
     */
    void deleteById(String specialtyId);

    /**
     * 逻辑删除
     *
     * @param specialtyId
     */
    void deleteIsValidById(String specialtyId);

    /**
     * 分页查询教师信息
     *
     * @param sortVo
     * @return
     */
    Page<Specialty> findAll(SortVo sortVo);
}
