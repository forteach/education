package com.forteach.education.common.keyword;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 定义系统全部常量和错误码
 * 
 */
public final class DefineCode {

	// 0-100通用错误码常量**********************
	//("成功")
	public static final int OK = 0;

	//("系统繁忙，序列化失败")
	public static final int CORE_ERR = 1;

	//("参数错误")
	public static final int ERR0002 = 2;

	//("解包请求消息失败")
	public static final int ERR0003 = 3;

	//("签名验证失败")
	public static final int ERR0004 = 4;
	
	//("签名信息超出长度")
	public static final int ERR0005 = 5;

	//("接口调用错误")
	public static final int ERR0009 = 9;

	//("参数不能为空")
	public static final int ERR0010 = 10;

	//("存储信息重复")
	public static final int ERR0011 = 11;

	//("批处理失败")
	public static final int ERR0012 = 12;

	//("操作失败")
	public static final int ERR0013 = 13;
	
	//("未找到查询对象")
	public static final int ERR0014 = 14;
	
	//("参数不能相等")
	public static final int ERR0015 = 15;
	
	//("原密码不对")
	public static final int ERR0016 = 16;

}
