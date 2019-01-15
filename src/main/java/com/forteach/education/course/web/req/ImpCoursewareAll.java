package com.forteach.education.course.web.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-3 17:09
 * @Version: 1.0
 * @Description: 课程教案、课件
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpCoursewareAll {

 private String chapterId;

 //资料领域：1教案 2课件
 private String importantType;

 //文件数量
 private int dCount;

 //1文件2、图集3、视频
 private String datumType;

 //图集名称
 private  String photoDatumName;

 //课件或教案文件列表
 private List<CoursewareAll> files;


}
