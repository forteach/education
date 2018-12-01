package com.forteach.education.web.req;

import com.forteach.education.web.vo.SortVo;
import lombok.*;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-30 15:33
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseSeachReq extends SortVo implements Serializable {
    private String worlds;
//    private
}
