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
    private int fileCount;

    //1文件 3、视频
    private String datumType;

    //图集名称
    private String photoDatumName;

    //课件或教案文件列表
    private List<CoursewareAll> files;

    private String createUser;

    public ImpCoursewareAll(String chapterId, String importantType, int fileCount, String datumType, String photoDatumName, List<CoursewareAll> files) {
        this.chapterId = chapterId;
        this.importantType = importantType;
        this.fileCount = fileCount;
        this.datumType = datumType;
        this.photoDatumName = photoDatumName;
        this.files = files;
    }
}
