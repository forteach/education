package com.forteach.education.course.web.req;


import lombok.Data;
import java.io.Serializable;

@Data
public class CoursewareAll implements Serializable {

    //文件编号（也可能是图集编号）
    public String id;

    public String fileName;
    //图集的话，可以没有URL
    public String fileUrl;

}
