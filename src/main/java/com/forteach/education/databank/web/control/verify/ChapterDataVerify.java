package com.forteach.education.databank.web.control.verify;

import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.databank.web.req.ChapteDataReq;
import org.springframework.stereotype.Component;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-26 14:55
 * @version: 1.0
 * @description:
 */
@Component
public class ChapterDataVerify {

    public void saveVerify(ChapteDataReq chapteDataReq){
        MyAssert.elt(0, chapteDataReq.getFiles().size(), DefineCode.ERR0010, "文件对象不为空");
        verify(chapteDataReq);
    }

    public void updateVerify(ChapteDataReq chapteDataReq){
        MyAssert.blank(chapteDataReq.getFileId(), DefineCode.ERR0010, "资料编号不为空");
        verify(chapteDataReq);
    }

    private void verify(ChapteDataReq chapteDataReq){
        MyAssert.blank(chapteDataReq.getChapterId(), DefineCode.ERR0010, "章节编号不为空");
        MyAssert.blank(chapteDataReq.getCourseId(), DefineCode.ERR0010, "科目编号不为空");
        MyAssert.blank(chapteDataReq.getDatumArea(), DefineCode.ERR0010, "资料领域不为空");
        MyAssert.blank(chapteDataReq.getDatumType(), DefineCode.ERR0010, "资料类型不为空");
        MyAssert.blank(chapteDataReq.getTeachShare(), DefineCode.ERR0010, "教师共享不为空");
        MyAssert.blank(chapteDataReq.getStuShare(), DefineCode.ERR0010, "学生共享不为空");
    }

}
