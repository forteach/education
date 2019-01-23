package com.forteach.education.databank.web.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-3 17:09
 * @Version: 1.0
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatumResp {

 //文件编号
 public String fileId;
//文件领域（预习,复习...）多领域逗号分隔
 public String datumArea;
//章节编号
 public String chapterId;
//文件名称
 public String fileName;
//文件扩展名
 public String fileType;
//文件URl
 public String fileUrl;
//文件类型（音频、视频....）
 private String datumType;
//教师共享
 private String teachShare="0";
//学生可见
 private String stuShare="0";

}
