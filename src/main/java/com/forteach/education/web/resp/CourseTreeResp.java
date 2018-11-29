package com.forteach.education.web.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 11:49
 * @Version: 1.0
 * @Description: 返回前端目录树结构
 */
@Builder
@ApiModel(value = "树状目录结构")
public class CourseTreeResp implements Serializable {

    @ApiModelProperty(value = "ID")
    private String id;
    @ApiModelProperty(value = "父ID")
    private String parent;

    @ApiModelProperty(value = "名称")
    private String text;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "是否打开", required = true, notes = "默认true 打开")
    private State state;

    public State getState() {
        return state;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public CourseTreeResp() {
    }

    public CourseTreeResp(String id, String text, String icon, Integer level, State state) {
        this.id = id;
        this.text = text;
        this.icon = icon;
        this.level = level;
        this.state = state;
    }

    public CourseTreeResp(String id, String parent, String text, String icon, Integer level, State state) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
        this.level = level;
        this.state = state;
    }
}
