package com.forteach.education.service;

import com.forteach.education.web.vo.FileUploadVo;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:40
 * @Version: 1.0
 * @Description:
 */
public interface  UploadService {

    T save(FileUploadVo fileUploadVo);
}
