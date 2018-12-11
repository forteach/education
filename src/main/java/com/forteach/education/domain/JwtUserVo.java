package com.forteach.education.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-11 11:35
 * @Version: 1.0
 * @Description:
 */
@Builder
@Data
@AllArgsConstructor
public class JwtUserVo implements Serializable {

    private String userId;

    private String username;

    private String role;
}
