package com.forteach.education.information.web.res.article;

public interface IArticleResponse {

    /**
     * 模块编号
     *
     * @auto zjw
     * @date 2015年10月26日 上午8:59:17
     */
    public abstract String getModId();

    public abstract void setModId(String modId);

    /**
     * 查询关键字
     *
     * @auto zjw
     * @date 2015年10月26日 上午8:59:34
     */
    public abstract String getKeyWord();

    public abstract void setKeyWord(String keyWord);

    /**
     * 显示标题
     *
     * @auto zjw
     * @date 2015年10月26日 上午9:00:07
     */
    public abstract String getTitle();

    public abstract void setTitle(String title);

    /**
     * 列表图片
     *
     * @auto zjw
     * @date 2015年10月26日 上午9:00:29
     */
    public abstract String getImgUrl();

    public abstract void setImgUrl(String imgUrl);

    /**
     * 点击量
     *
     * @auto zjw
     * @date 2015年10月26日 上午9:00:44
     */
    public abstract int getClickCount();

    public abstract void setClickCount(int clickCount);

    /**
     * 是否有效
     *
     * @auto zjw
     * @date 2015年10月26日 上午9:00:53
     */
    public abstract String getIsValidated();

    public abstract void setIsValidated(String isValidated);

}