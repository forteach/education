package com.forteach.education.common.service.impl;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.authority.repository.StudentRepository;
import com.forteach.education.authority.web.req.FindAllPageStudentReq;
import com.forteach.education.common.service.StudentService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.forteach.education.common.keyword.Dic.STUDENT_ADO;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-14 14:41
 * @version: 1.0
 * @description:
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final HashOperations<String, String, String> hashOperations;
    private final StudentRepository studentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentServiceImpl(HashOperations<String, String, String> hashOperations, StudentRepository studentRepository) {
        this.hashOperations = hashOperations;
        this.studentRepository = studentRepository;
    }

    private String getStudentKey(String studentId){
        return STUDENT_ADO.concat(studentId);
    }

    /**
     * 通过学生id 获取学生信息
     * @param studentId
     * @return
     */
    @Override
    public StudentEntitys getStudentEntitysById(String studentId){
        String key = getStudentKey(studentId);
        String studentName = hashOperations.get(key, "name");
        String classId = hashOperations.get(key, "classId");
        String portrait = hashOperations.get(key, "portrait");
        return StudentEntitys.builder()
                .classId(classId)
                .portrait(portrait)
                .userName(studentName)
                .id(studentId)
                .build();
    }

    /**
     * 学生集合list ==> 获取学生对象信息
     * @param stringList
     * @return
     */
    @Override
    public List<StudentEntitys> getStudentEntitysList(List<String> stringList){
        List<StudentEntitys> studentEntityslist = Lists.newArrayList();
        for (String string :stringList) {
        	studentEntityslist.add(getStudentEntitysById(string));
        }
        return studentEntityslist;
    }

    /**
     * "," 逗号分隔获取学生对象信息
     * @param studentIds
     * @return
     */
    @Override
    public List<StudentEntitys> getStudentListByStr(String studentIds){
        return getStudentEntitysList(Arrays.asList(studentIds.split(",")));
    }

    @Override
    public Page<StudentEntitys> findAllPage(FindAllPageStudentReq req) {
        PageRequest of = PageRequest.of(req.getPage(), req.getSize());
        StringBuilder dataSql = new StringBuilder("select " +
                " c.class_id as class_id, c.class_name as class_name, s.id as id, s.user_name as user_name, s.portrait as portrait, " +
                " s.birth_date as birth_date, s.gender as gender, s.grade as grade, s.id_card_no as id_card_no, s.phone as phone, s.student_status as student_status, " +
                " s.c_time as c_time, s.u_time as u_time, s.u_user as u_user, s.is_validated as is_validated, s.c_user as c_user, s.is_graduate as is_graduate from student_info as s left join classes as c on s.class_id = c.class_id ");
        StringBuilder whereSql = new StringBuilder(" where s.is_validated = '0' ");
        StringBuilder countSql = new StringBuilder(" select count(1) from student_info as s left join classes as c on s.class_id = c.class_id ");
        if (StrUtil.isNotBlank(req.getStudentName())){
            whereSql.append(" and s.user_name = :studentName");
        }
        if (StrUtil.isNotBlank(req.getClassName())){
            whereSql.append(" and c.class_name = :className");
        }
        dataSql.append(whereSql).append(" order by s.c_time desc");
        countSql.append(whereSql);

        Query dataQuery = entityManager.createNativeQuery(dataSql.toString(), StudentEntitys.class);
        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        if (StrUtil.isNotBlank(req.getStudentName())){
            dataQuery.setParameter("studentName", req.getStudentName());
            countQuery.setParameter("studentName", req.getStudentName());
        }
        if (StrUtil.isNotBlank(req.getClassName())){
            dataQuery.setParameter("className", req.getClassName());
            countQuery.setParameter("className", req.getClassName());
        }
        //设置分页
        dataQuery.setFirstResult((int)of.getOffset());
        dataQuery.setMaxResults(of.getPageSize());
        BigInteger count = (BigInteger) countQuery.getSingleResult();
        long total = count.longValue();
        List<StudentEntitys> content = total > of.getOffset() ? dataQuery.getResultList() : Collections.emptyList();
        return new PageImpl<>(content, of, total);
    }
}
