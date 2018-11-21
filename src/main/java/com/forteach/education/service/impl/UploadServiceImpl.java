package com.forteach.education.service.impl;

import com.forteach.education.repository.AudioDatumRepository;
import com.forteach.education.repository.FileDatumRepository;
import com.forteach.education.repository.LinkDatumRepository;
import com.forteach.education.repository.ViewDatumRepository;
import com.forteach.education.service.UploadService;
import com.forteach.education.web.vo.FileUploadVo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:43
 * @Version: 1.0
 * @Description:
 */
public class UploadServiceImpl implements UploadService {
    @Autowired
    private LinkDatumRepository linkDatumRepository;
    @Autowired
    private AudioDatumRepository audioDatumRepository;
    @Autowired
    private FileDatumRepository fileDatumRepository;
    @Autowired
    private ViewDatumRepository viewDatumRepository;
    @Override
    public T save(FileUploadVo fileUploadVo) {
//        switch (fileUploadVo.getFileType()){
//            case 1:
//                fileDatumRepository.save(FileDatum.builder().)
//        }
        return null;
    }
}
