package com.forteach.education.exception;

public class AssertErrorException extends GetGeoLocationException {

    private static final long serialVersionUID = -6924564120267866554L;

    /**
     * 错误码.
     */
    private final int errorCode;

    /**
     * 构造函数.
     *
     * @param errorCode 错误码
     * @param message   错误码描述
     */
    public AssertErrorException(final int errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数.
     *
     * @param errorCode 错误码
     * @param cause     cause exception
     */
    public AssertErrorException(final int errorCode, final Throwable cause) {
        this(errorCode, cause.getMessage(), cause);
    }

    /**
     * 构造函数.
     *
     * @param errorCode 错误码
     * @param message   错误码描述
     * @param cause     cause exception
     */
    public AssertErrorException(int errorCode, String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

}
