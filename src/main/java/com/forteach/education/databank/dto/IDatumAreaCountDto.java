package com.forteach.education.databank.dto;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:  根据课程领域返回资料类型的个数
 */

public interface IDatumAreaCountDto {

    /**
     *资料类型
     * @return
     */
    public String getDatumType();

    /**
     *count数量
     * @return
     */
    public String getDCount();

}
