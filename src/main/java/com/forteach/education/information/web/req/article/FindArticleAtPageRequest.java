package com.forteach.education.information.web.req.article;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindArticleAtPageRequest  implements Serializable {

	private int pageNo;
	/**分类编号**/
	private String catId;

}
