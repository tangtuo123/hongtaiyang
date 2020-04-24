package com.hongtaiyang.common.exception;


import com.hongtaiyang.common.enums.SystemCode;

/**
 * @author zhuxj
 * @description 异常类，交换过程中采用统一异常抛出
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 1450380739762358985L;
    private SystemCode errorCode;
    private Object data;

    private SysException(SystemCode error) {
        super(error.getDescribe());
        this.errorCode = error;
    }

    private SysException(SystemCode error, Object data) {
        super(error.getDescribe());
        this.errorCode = error;
        this.data = data;
    }

    private SysException(SystemCode error, String tips) {
        super(error.getCode() + "-" + error.getDescribe() + "-" + tips);
        this.errorCode = error;
    }

    public static SysException asException(SystemCode errorCode) {
        return new SysException(errorCode);
    }

    public static SysException asException(SystemCode errorCode, Object data, int type) {
        return new SysException(errorCode, data);
    }

    public static SysException asException(SystemCode errorCode, String tips) {
        return new SysException(errorCode, tips);
    }

    public SystemCode getErrorCode() {
        return errorCode;
    }

    public Object getData() {
        return data;
    }

    public String getMessageWithTraceLine() {
        StringBuilder message = new StringBuilder(getMessage() + "\n\n" + this);
        StackTraceElement[] trace = this.getStackTrace();
        for (StackTraceElement traceElement : trace) {
            message.append("\n\tat ").append(traceElement);
        }
        return message.append("\n\n").toString();
    }
}
