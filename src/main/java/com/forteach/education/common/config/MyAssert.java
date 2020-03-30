package com.forteach.education.common.config;

import java.util.Collection;

import cn.hutool.core.util.StrUtil;

import com.forteach.education.exception.AssertErrorException;

/**
 * 服务断言.
 */
public final class MyAssert {
    /**
     * 构造函数.
     */
    private MyAssert() {

    }


    /**
     * 断言失败,简单抛出异常.
     *
     * @param reason 失败原因
     * @param format 格式化字符串，用来生成异常信息
     * @param args   格式化字符串的参数
     * @throws AssertErrorException
     */
    public static void fail(int reason, String format, Object... args) {

        throw new AssertErrorException(reason, String.format(format, args));

    }

    /**
     * @param reason 失败原因
     * @param t      嵌套异常
     * @param format 格式化字符串，用来生成异常信息
     * @param args   格式化字符串的参数
     */
    public static void fail(int reason, Throwable t, String format,
                            Object... args) {
        fail(reason, t, String.format(format, args));
    }

    /**
     * 断言失败,简单抛出ServiceAssertError异常.
     *
     * @param reason  失败原因
     * @param t       嵌套异常
     * @param message 错误描述
     */
    public static void fail(int reason, Throwable t, String message) {
        throw new AssertErrorException(reason, message, t);
    }

    /**
     * 断言测试的值为true，则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void isTrue(boolean value, int errcode) {
        isTrue(value, errcode, null);
    }

    /**
     * 断言测试的值为true，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void isTrue(boolean value, int errcode, String format,
                              Object... args) {

        if (value) {
            fail(errcode, String.format(format, args));
        }

    }

    /**
     * 断言测试的值为true，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void isTrue(boolean value, int errcode, String message) {
        if (value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言测试的值为false，则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void isFalse(boolean value, int errcode) {
        isFalse(value, errcode, null);
    }

    /**
     * 断言测试的值为false，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void isFalse(boolean value, int errcode, String format,
                               Object... args) {
        if (!value) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言测试的值为false，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void isFalse(boolean value, int errcode, String message) {
        if (!value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言测试的值为null，则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static <T> void isNull(final T value, int errcode) {
        isNull(value, errcode, null);
    }

    /**
     * 断言测试的值为null，否则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static <T> void isNull(final T value, int errcode, String format,
                                  Object... args) {

        if (null == value) {
            fail(errcode, String.format(format, args));
        }

    }

    /**
     * 断言测试的值为null，则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static <T> void isNull(final T value, int errcode, String message) {
        if (null == value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言测试的值不为null，则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static <T> void notNull(final T value, int errcode) {
        notNull(value, errcode, null);
    }

    /**
     * 断言测试的值不为null，否则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static <T> void notNull(final T value, int errcode, String format,
                                   Object... args) {

        if (null != value) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言测试的值不为null，否则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static <T> void notNull(final T value, int errcode, String message) {
        if (null != value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言测试的字符串不为空，则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void notBlank(String value, int errcode) {
        notBlank(value, errcode, null);
    }

    /**
     * 断言测试的字符串不为空，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void notBlank(String value, int errcode, final String format,
                                Object... args) {

        if (StrUtil.isNotBlank(value)) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言测试的字符串不为空，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void notBlank(String value, int errcode, final String message) {
        if (StrUtil.isNotBlank(value)) {
            fail(errcode, message);
        }
    }

    /**
     * 断言测试的字符串不为空，否则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void blank(String value, int errcode) {
        blank(value, errcode, null);
    }

    /**
     * 断言测试的字符串为空，则抛出异常.
     *
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void blank(String value, int errcode, final String message) {
        if (StrUtil.isBlank(value)) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value == expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void eq(int value, int expect, int errcode) {
        eq(value, expect, errcode, null);
    }

    /**
     * 断言value == expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void eq(int value, int expect, int errcode,
                          final String message) {
        if (expect == value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value == expect，否则抛出异常.
     *
     * @param value   待测试的值
     * @param expect  断言的值
     * @param errcode 异常错误码
     */
    public static void eq(String value, String expect, int errcode) {
        eq(value, expect, errcode, null);
    }

    /**
     * 断言value == expect，则抛出异常.
     *
     * @param <T>     变量类型
     * @param value   待测试的值
     * @param expect  断言的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static <T> void eq(String value, String expect, int errcode,
                              String format, Object... args) {
        if (args.length == 0) {
            eq(value, expect, errcode, format);
        } else {
            eq(value, expect, errcode, String.format(format, args));
        }

    }

    /**
     * 断言value == expect，则抛出异常.
     *
     * @param value   待测试的值
     * @param expect  断言的值
     * @param errcode 异常错误码
     * @param message 消息
     */
    private static void eq(String value, String expect, int errcode,
                           final String message) {

        if (StrUtil.equals(value, expect)) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value == expect，否则抛出异常.
     *
     * @param value   待测试的值
     * @param expect  断言的值
     * @param errcode 异常错误码
     */
    public static void notEq(String value, String expect, int errcode) {
        notEq(value, expect, errcode, null);
    }

    /**
     * 断言value != expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void notEq(int value, int expect, int errcode) {
        notEq(value, expect, errcode, null);
    }

    /**
     * 断言value != expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void notEq(int value, int expect, int errcode,
                             final String message) {
        if (expect != value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value == expect，则抛出异常.
     *
     * @param value   待测试的值
     * @param expect  断言的值
     * @param errcode 异常错误码
     * @param message 消息
     */
    public static void notEq(String value, String expect, int errcode,
                             final String message) {

        if (!StrUtil.equals(value, expect)) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value == expect，区分大小写,否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void ne(String value, String expect, int errcode) {
        ne(value, expect, errcode, null);
    }

    /**
     * 断言value == expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void ne(String value, String expect, int errcode,
                          String format, Object... args) {
        ne(value, expect, errcode, String.format(format, args));
    }

    /**
     * 断言value == expect，不区分大小写，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void ne(String value, String expect, int errcode,
                          final String message) {

        if (StrUtil.equalsIgnoreCase(value, expect)) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value != expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void ne(int value, int expect, int errcode) {
        ne(value, expect, errcode, null);
    }

    /**
     * 断言value == expect，不区分大小写则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void ne(int value, int expect, int errcode, String format,
                          Object... args) {

        if (expect == value) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value != expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void ne(int value, int expect, int errcode,
                          final String message) {

        if (expect == value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value 小于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void gt(int value, int expect, int errcode) {
        gt(value, expect, errcode, null);
    }

    /**
     * 断言value 小于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void gt(int value, int expect, int errcode, String format,
                          Object... args) {
        if (value < expect) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value 小于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void gt(int value, int expect, int errcode,
                          final String message) {
        if (value < expect) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value 小于等于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void egt(int value, int expect, int errcode) {
        egt(value, expect, errcode, null);
    }

    /**
     * 断言value 小于等于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void egt(int value, int expect, int errcode, String format,
                           Object... args) {
        if (value <= expect) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value &gt;= expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void egt(int value, int expect, int errcode,
                           final String message) {
        if (value <= expect) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value 大于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void lt(int value, int expect, int errcode) {
        lt(value, expect, errcode, null);
    }

    /**
     * 断言value 大于 expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void lt(int value, int expect, int errcode, String format,
                          Object... args) {
        if (value > expect) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value 大于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void lt(long value, long expect, int errcode,
                          final String message) {
        if (value > expect) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value &lt; expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void lt(long value, long expect, int errcode) {
        lt(value, expect, errcode, null);
    }

    /**
     * 断言value &lt; expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void lt(long value, long expect, int errcode, String format,
                          Object... args) {
        if (value >= expect) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value 大于 expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void lt(int value, int expect, int errcode,
                          final String message) {
        if (value > expect) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value 大于等于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void elt(int value, int expect, int errcode) {
        elt(value, expect, errcode, null);
    }

    /**
     * 断言value 大于等于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void elt(int value, int expect, int errcode, String format,
                           Object... args) {
        if (value >= expect) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value 大于等于 expect，则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void elt(int value, int expect, int errcode,
                           final String message) {
        if (value >= expect) {
            fail(errcode, message);
        }
    }

    /**
     * 断言value &lt;= expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     */
    public static void elt(long value, long expect, int errcode) {
        elt(value, expect, errcode, null);
    }

    /**
     * 断言value &lt;= expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void elt(long value, long expect, int errcode, String format,
                           Object... args) {
        if (value >= expect) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言value &lt;= expect，否则抛出异常.
     *
     * @param expect  断言的值
     * @param value   待测试的值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void elt(long value, long expect, int errcode,
                           final String message) {
        if (value >= expect) {
            fail(errcode, message);
        }
    }

    /**
     * 断言start &lt;= value &lt;= end(左右都为闭区间)，则抛出异常.
     *
     * @param value   待测试的值
     * @param start   起始值
     * @param end     结束值
     * @param errcode 异常错误码
     */
    public static void between(int value, int start, int end, int errcode) {
        between(value, start, end, errcode, null);
    }

    /**
     * 断言start &lt;= value &lt;= end(左右都为闭区间)，否则抛出异常.
     *
     * @param value   待测试的值
     * @param start   起始值
     * @param end     结束值
     * @param errcode 异常错误码
     * @param format  格式化字符串，用来生成异常信息
     * @param args    格式化字符串的参数
     */
    public static void between(int value, int start, int end, int errcode,
                               String format, Object... args) {
        if (((value >= start) && (value <= end))) {
            fail(errcode, String.format(format, args));
        }
    }

    /**
     * 断言start &lt;= value &lt;= end(左右都为闭区间)，则抛出异常.
     *
     * @param value   待测试的值
     * @param start   起始值
     * @param end     结束值
     * @param errcode 异常错误码
     * @param message 异常错误消息
     */
    public static void between(int value, int start, int end, int errcode,
                               final String message) {
        if (((value >= start) && (value <= end))) {
            fail(errcode, message);
        }
    }

    /**
     * 断言item包含在collection，否则抛出异常.
     *
     * @param <T>        变量类型
     * @param collection 要测试容器
     * @param value      要测试的对象
     * @param errcode    异常错误码
     */
    public static <T> void in(T value, Collection<T> collection, int errcode) {
        in(value, collection, errcode, null);
    }

    /**
     * 断言item包含在collection, 否则抛出异常.
     *
     * @param <T>        变量类型
     * @param collection 要测试容器
     * @param value      要测试的对象
     * @param errcode    异常错误码
     * @param message    异常错误消息
     */
    public static <T> void in(T value, Collection<T> collection, int errcode,
                              final String message) {
        isNull(collection, errcode, message);
        if (collection.contains(value)) {
            fail(errcode, message);
        }
    }


    /**
     * 断言item不包含在collection，则抛出异常.
     *
     * @param <T>        变量类型
     * @param collection 要测试容器
     * @param value      要测试的对象
     * @param errcode    异常错误码
     */
    public static <T> void notIn(T value, Collection<T> collection, int errcode) {
        notIn(value, collection, errcode, null);
    }

    /**
     * 断言item不包含在collection, 则抛出异常.
     *
     * @param <T>        变量类型
     * @param collection 要测试容器
     * @param value      要测试的对象
     * @param errcode    异常错误码
     * @param message    异常错误消息
     */
    public static <T> void notIn(T value, Collection<T> collection,
                                 int errcode, final String message) {
        isNull(collection, errcode, message);
        if (collection.contains(value)) {
            fail(errcode, message);
        }
    }

}
