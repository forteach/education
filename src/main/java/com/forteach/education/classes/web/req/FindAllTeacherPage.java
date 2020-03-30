package com.forteach.education.classes.web.req;

import com.forteach.education.common.web.vo.SortVo;
import lombok.Data;

@Data
public class FindAllTeacherPage extends SortVo {
    private String teacherName;
}
