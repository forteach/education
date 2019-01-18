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

 public String fileId;

 public String dataId;

 public String datumArea;

 public String chapterId;

 public String fileName;

 public String fileType;

 public String fileUrl;

 private String datumType;

 private String teachShare="0";

 private String stuShare="0";

}
