package com.forteach.education.filter;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-30 17:00
 * @Version: 1.0
 * @Description: jackjson 序列化使用
 */
public class View {

    public interface Summary {
    }

    public interface SummaryExtend extends Summary {
    }

    public interface SummaryDetail extends SummaryExtend {
    }
}
