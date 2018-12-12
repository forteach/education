package com.forteach.education.web.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 14:40
 * @Version: 1.0
 * @Description:
 */
@ApiModel(value = "树型菜单是否展开, 默认是展开")
public class State {

    @ApiModelProperty(value = "是否展开", notes = "默认打开true")
    private final boolean opened = true;

    @ApiModelProperty(value = "是否选中", name = "选中状态")
    private boolean selected;

    public boolean isOpened() {
        return opened;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public State() {
    }

    public State(boolean selected) {
        this.selected = selected;
    }
}