package com.forteach.education.common.keyword;

public enum ZiLiao {
    FILE(1), PHOTO(2), VIEW(3), AUDIO(4), LINK(5);

    //必须增加一个构造函数,变量,得到该变量的值
    private int  mState=1;
    private ZiLiao(int value)
    {
        mState=value;
    }
    /**
     * @return 枚举变量实际返回值
     */
    public  int getState()
    {
        return mState;
    }
}
