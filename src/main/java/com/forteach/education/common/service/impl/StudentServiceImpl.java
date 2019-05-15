package com.forteach.education.common.service.impl;
import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.common.service.StudentService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    public StudentServiceImpl(HashOperations<String, String, String> hashOperations) {
        this.hashOperations = hashOperations;
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
}
