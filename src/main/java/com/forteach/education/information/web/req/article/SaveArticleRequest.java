package com.forteach.education.information.web.req.article;


import com.forteach.education.web.vo.DataDatumVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * 创建资讯
 * 
 * @author zjw
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveArticleRequest implements Serializable {
	
	/** 文章类型. */
	public static String STATUS_TEXT = "txt";
	
	/** 链接类型. */
	public static String STATUS_LINK = "link";

	/** 图片类型. */
	public static String STATUS_IMAGE = "img";

	/** 文章编号.**/
	private String articleId;

	/** 课程编号.**/
	private String courseId;

	/** 发布人编号.**/
	private String userId;

	/** 班级编号.**/
	private String classId;

	/** 文章题目.**/
	private String title;

	// 图片连接.**/
	private String imgUrl;

	// 文章连接.**/
	private String linkUrl;

	/** 文章描述.**/
	private String description;

	/** 文章内容.**/
	private String articleConten;

	/** 资讯分类. **/
	private String articleType;

	@ApiModelProperty(value = "图片信息", name = "images", required = true)
	private List<DataDatumVo> images;

}