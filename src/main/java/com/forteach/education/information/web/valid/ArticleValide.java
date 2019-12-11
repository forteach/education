package com.forteach.education.information.web.valid;

import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.information.web.req.article.SaveArticleRequest;

public class ArticleValide {
	public static void saveValide(SaveArticleRequest request){
		String articleType=request.getArticleType();
		String title=request.getTitle();
		String courseId=request.getCourseId();
		String classId=request.getClassId();
		String userId=request.getUserId();
		
		MyAssert.blank(articleType, DefineCode.ERR0010,"资讯数据类型不能为空");
		MyAssert.blank(title, DefineCode.ERR0010,"资讯标题不能为空");
		MyAssert.blank(courseId, DefineCode.ERR0010,"课程编号不能为空");
		MyAssert.blank(classId, DefineCode.ERR0010,"班级编号不能为空");
		MyAssert.blank(userId, DefineCode.ERR0010,"资讯发布人信息不能为空");
	}
}