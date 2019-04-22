package com.forteach.education.information.web.req.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 资讯详情查询
 * 
 * @author zjw
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByIdRequest implements Serializable {

	/** 主键编号. **/
	private String id;
}