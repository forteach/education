package com.forteach.education.databank.dto;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */

public interface IChapterDataListDto {

    /**
     *文件编号
     * @return
     */
    public String getDataId();

    /**
     *资料名称
     * @return
     */
    public String getDatumName();

    /**
     * 课程ID
     * @return
     */
    public String getCourseId();

    /**
     *章节编号
     * @return
     */
    public String getChapterId();


    /**
     *资料类型
     * @return
     */
    public String getDatumType();

    /**
     *资料领域
     * @return
     */
    public String getDatumArea();


}
