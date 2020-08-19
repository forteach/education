package com.forteach.education.exception;

public class GetGeoLocationException extends RuntimeException {

    /**
     * 获得全局异常
     */
    private static final long serialVersionUID = 1L;

    public GetGeoLocationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public GetGeoLocationException(String message) {
        super(message);
    }

    /**
     * 构造函数.
     *
     * @param cause 引起此异常的异常
     */
    public GetGeoLocationException(Throwable cause) {
        super(cause);
    }


    /**
     * 构造函数.
     *
     * @param message            异常信息
     * @param cause              引起此异常的异常
     * @param enableSuppression  是否启用suppression
     * @param writableStackTrace 堆栈信息是否可写
     */
    public GetGeoLocationException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
