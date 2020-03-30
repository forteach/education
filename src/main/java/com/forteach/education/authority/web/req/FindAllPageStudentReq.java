package com.forteach.education.authority.web.req;

import com.forteach.education.common.web.vo.SortVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-11-26 17:57
 * @version: 1.0
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FindAllPageStudentReq extends SortVo {

    private String studentName;

    private String className;
}