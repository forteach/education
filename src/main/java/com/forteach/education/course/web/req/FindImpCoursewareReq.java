package com.forteach.education.course.web.req;

import com.forteach.education.course.domain.ziliao.AbsFile;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class FindImpCoursewareReq implements Serializable {

    private String chapterId;

    private String importantType;

    private String datumType;

}
